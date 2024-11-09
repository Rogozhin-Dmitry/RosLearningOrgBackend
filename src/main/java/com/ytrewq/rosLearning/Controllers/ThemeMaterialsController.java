package com.ytrewq.rosLearning.Controllers;


import com.ytrewq.rosLearning.DTOs.ThemeMaterialDto;
import com.ytrewq.rosLearning.Entities.Course;
import com.ytrewq.rosLearning.Entities.Theme;
import com.ytrewq.rosLearning.Entities.ThemeMaterial;
import com.ytrewq.rosLearning.Entities.User;
import com.ytrewq.rosLearning.Exeptions.AppException;
import com.ytrewq.rosLearning.Forms.ThemeMaterialForm;
import com.ytrewq.rosLearning.Services.ThemeMaterialService;
import com.ytrewq.rosLearning.Services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ThemeMaterialsController {
    @Autowired
    ThemeMaterialService themeMaterialService;
    @Autowired
    ThemeService themeService;

    @GetMapping("/user/getThemeMaterials")
    public List<ThemeMaterialDto> getAllThemeMaterials(@AuthenticationPrincipal User user,
                                                       @RequestParam(name = "course_id") int courseId,
                                                       @RequestParam(name = "theme_id") int themeId) {
        List<ThemeMaterialDto> materials = themeMaterialService.getThemeMaterials(user, courseId, themeId);
        if (materials == null) {
            throw new AppException("Theme not allow for user");
        }
        return materials;
    }

    @GetMapping("/admin/getThemeMaterials")
    public List<ThemeMaterialDto> getAllThemeMaterialsAdmin(@AuthenticationPrincipal User user,
                                                            @RequestParam(name = "theme_id") int themeId) {
        List<ThemeMaterialDto> materials = themeMaterialService.getThemeMaterialsAdmin(themeId);
        if (materials == null) {
            throw new AppException("Theme is not exists");
        }
        return materials;
    }

    @GetMapping("/admin/getAllMaterials")
    public List<ThemeMaterialDto> getAllMaterials(@AuthenticationPrincipal User user) {
        return themeMaterialService.getAllMaterials();
    }

    @PostMapping("/admin/createThemeMaterial")
    public Map<String, String> createThemeMaterial(@RequestBody ThemeMaterialForm form) {
        ThemeMaterial themeMaterial = new ThemeMaterial();
        themeMaterial.setTitle(form.getTitle());
        themeMaterial.setMaterialType(form.getMaterialType());
        themeMaterial.setMaterialURL(form.getMaterialURL());
        themeMaterial.setMaterialText(form.getMaterialText());
        themeMaterialService.save(themeMaterial);

        HashMap<String, String> map = new HashMap<>();
        map.put("result", "all_ok");
        return map;
    }

    @GetMapping("/admin/addThemeMaterial")
    public Map<String, String> addThemeMaterial(@RequestParam(name = "theme_id") int themeId,
                                                @RequestParam(name = "material_id") int materialId) {

        Theme theme = themeService.getThemeAdmin(themeId);
        if (theme == null) {
            throw new AppException("Theme not found.");
        }


        if (!themeMaterialService.existsById(themeId)) {
            throw new AppException("ThemeMaterial not found.");
        }

        themeMaterialService.addThemeMaterial(theme, materialId);

        HashMap<String, String> map = new HashMap<>();
        map.put("result", "all_ok");
        return map;
    }

    @GetMapping("/admin/removeThemeMaterial")
    public Map<String, String> removeThemeMaterial(@RequestParam(name = "theme_id") int themeId,
                                                  @RequestParam(name = "material_id") int materialId) {

        Theme theme = themeService.getThemeAdmin(themeId);
        if (theme == null) {
            throw new AppException("Theme not found.");
        }


        if (!themeMaterialService.existsById(themeId)) {
            throw new AppException("ThemeMaterial not found.");
        }

        themeMaterialService.removeThemeMaterial(theme, materialId);

        HashMap<String, String> map = new HashMap<>();
        map.put("result", "all_ok");
        return map;
    }
}
