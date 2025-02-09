package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.dto.FullCentersDTO;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.entity.Profile;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.ProfileRepository;
import com.example.bootcamp.repository.UsersRepository;
import com.example.bootcamp.service.CentersService;
import com.example.bootcamp.util.CentersMapper;
import com.example.bootcamp.util.FullCentersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CentersServiceImpl implements CentersService {

    private final CenterRepository centerRepository;
    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;

    @Override
    public List<CentersDTO> getAllCenters() {
        return centerRepository.findAll().stream()
                .map(CentersMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CentersDTO getCenterById(Long id) {
        Optional<Center> center = centerRepository.findById(id);
        return center.map(CentersMapper::convertDTO).orElse(null);
    }

    @Override
    public FullCentersDTO getFullCenterById(Long id) {
        Optional<Center> center = centerRepository.findById(id);
        return center.map(FullCentersMapper::convertDTO).orElse(null);
    }

    @Override
    public CentersDTO createCenter(CentersDTO dto) {
        Center center = new Center();
        center.setName(dto.getName());
        center.setAddress(dto.getAddress());
        center.setLatitude(dto.getLatitude());
        center.setLongitude(dto.getLongitude());

        Center savedCenter = centerRepository.save(center);
        return CentersMapper.convertDTO(savedCenter);
    }


    @Override
    public CentersDTO updateCenter(Long id, CentersDTO dto) {
        Optional<Center> existingCenterOptional = centerRepository.findById(id);
        if (existingCenterOptional.isPresent()) {
            Center existingCenter = existingCenterOptional.get();
            existingCenter.setName(dto.getName());
            existingCenter.setAddress(dto.getAddress());
            existingCenter.setLatitude(dto.getLatitude());
            existingCenter.setLongitude(dto.getLongitude());
            Center updatedCenter = centerRepository.save(existingCenter);
            return CentersMapper.convertDTO(updatedCenter);
        }
        return null;
    }

    @Override
    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }


    @Override
    public Page<CentersDTO> getAllCentersPaginated(Pageable pageable) {
        return centerRepository.findAll(pageable)
                .map(CentersMapper::convertDTO);
    }

    @Transactional
    @Override
    public String punishUserToCenter(Long profileid, Long centerid) {

        Optional<Center> existingCenterOptional = centerRepository.findById(centerid);
        Optional<Profile> existingUsersOptional = profileRepository.findById(profileid);

        if (existingCenterOptional.isPresent() && existingUsersOptional.isPresent()) {
            Center existCenter = existingCenterOptional.get();
            Profile existingUser = existingUsersOptional.get();

            List<Long> volunteersIds = existCenter.getVolunteer_ids();
            System.out.println("Current volunteer IDs: " + volunteersIds);

            if (volunteersIds == null) {
                volunteersIds = new ArrayList<>();
            }

            volunteersIds.add(profileid);

            System.out.println("Current volunteer IDs: " + volunteersIds);

            existCenter.setVolunteer_ids(volunteersIds);


            existingUser.setCenter(existCenter);
            centerRepository.save(existCenter);
            centerRepository.flush();

            return "User has been punished and added to the center";
        }

        return "Center or user not found";
    }
}

