package com.ytrewq.rosLearning.Services;

import com.ytrewq.rosLearning.DTOs.ThemeMaterialDto;
import com.ytrewq.rosLearning.Entities.Theme;
import com.ytrewq.rosLearning.Entities.ThemeMaterial;
import com.ytrewq.rosLearning.Entities.User;
import com.ytrewq.rosLearning.Repositories.ThemeMaterialRepository;
import com.ytrewq.rosLearning.Repositories.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeMaterialService {
    @Autowired
    private final ThemeRepository themeRepository;
    @Autowired
    private final ThemeMaterialRepository themeMaterialRepository;
    @Autowired
    private final ThemeService themeService;

    ModelMapper modelMapper = new ModelMapper();

    public ThemeMaterialService(ThemeRepository themeRepository, ThemeMaterialRepository themeMaterialRepository, ThemeService themeService) {
        this.themeRepository = themeRepository;
        this.themeMaterialRepository = themeMaterialRepository;
        this.themeService = themeService;
    }

    public List<ThemeMaterial> getThemeMaterials(Theme theme) {
        String[] materialsIdsStr = theme.getMaterialsIdsStr().split("/;/");
        List<Integer> materialsIds = new ArrayList<>();
        for (String s : materialsIdsStr) {
            materialsIds.add(Integer.parseInt(s));
        }
        return (List<ThemeMaterial>) themeMaterialRepository.findAllById(materialsIds);
    }

    public void setThemeMaterials(Theme theme, List<ThemeMaterial> materials) {
        List<String> materialsIdsStr = new ArrayList<>();
        for (ThemeMaterial material : materials) {
            materialsIdsStr.add(String.valueOf(material.getId()));
        }
        theme.setMaterialsIdsStr(String.join("/;/", materialsIdsStr));
        themeRepository.save(theme);
    }

    public ThemeMaterial getThemeMaterial(Theme theme, Integer materialId) {
        String materialIdStr = materialId.toString();
        for (String materialIdStrI : theme.getMaterialsIdsStr().split("/;/")) {
            if (materialIdStrI.equals(materialIdStr)) {
                Optional<ThemeMaterial> material = themeMaterialRepository.findById(materialId);
                return material.orElse(null);
            }
        }
        return null;
    }

    public void addThemeMaterial(Theme theme, ThemeMaterial material) {
        String courseId = String.valueOf(material.getId());
        if (theme.getMaterialsIdsStr() == null) {
            theme.setMaterialsIdsStr("");
        }
        if (!theme.getMaterialsIdsStr().isEmpty()) {
            theme.setMaterialsIdsStr(theme.getMaterialsIdsStr() + "/;/" + courseId);
        } else {
            theme.setMaterialsIdsStr(courseId);
        }
        themeRepository.save(theme);
    }

    public List<ThemeMaterialDto> getThemeMaterials(User currentUser, Integer courseId, Integer themeId) {
        Theme theme = themeService.getCourseTheme(currentUser, courseId, themeId);
        if (theme != null) {
            return getThemeMaterials(theme).stream().map(material -> modelMapper.map(material, ThemeMaterialDto.class)).toList();
        }
        return null;
    }

    public List<ThemeMaterialDto> getThemeMaterialsAdmin(Integer themeId) {
        Theme theme = themeService.getThemeAdmin(themeId);
        if (theme != null) {
            return getThemeMaterials(theme).stream().map(material -> modelMapper.map(material, ThemeMaterialDto.class)).toList();
        }
        return null;
    }

    public List<ThemeMaterialDto> getAllMaterials() {
        return themeMaterialRepository.findAll().stream().map(material -> modelMapper.map(material, ThemeMaterialDto.class)).toList();
    }

    public void save(ThemeMaterial themeMaterial) {
        themeMaterialRepository.save(themeMaterial);
    }
}
