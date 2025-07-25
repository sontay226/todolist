package com.sontaypham.todolist.Controller;

import com.sontaypham.todolist.DTO.Request.PermissionRequest;
import com.sontaypham.todolist.DTO.Response.ApiResponse;
import com.sontaypham.todolist.DTO.Response.PermissionResponse;
import com.sontaypham.todolist.Service.PermissionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionController {
  @Autowired PermissionService permissionService;

  @PostMapping("/create")
  ApiResponse<PermissionResponse> createPermission(
      @RequestBody @Valid PermissionRequest permissionRequest) {
    return ApiResponse.<PermissionResponse>builder()
        .status(1)
        .message("<Create Permission Successfully>")
        .data(permissionService.createPermission(permissionRequest))
        .build();
  }

  @GetMapping("/list")
  ApiResponse<List<PermissionResponse>> listPermission() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("<Username>" + authentication.getName());
    authentication
        .getAuthorities()
        .forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
    return ApiResponse.<List<PermissionResponse>>builder()
        .status(1)
        .message("<List Permission Successfully>")
        .data(permissionService.getPermissions())
        .build();
  }

  @GetMapping("/findByName/{name}")
  ApiResponse<PermissionResponse> findPermissionByName(@PathVariable String name) {
    return ApiResponse.<PermissionResponse>builder()
        .status(1)
        .message("<Find Permission Successfully>")
        .data(permissionService.findByName(name))
        .build();
  }

  @GetMapping("/existsByName/{name}")
  ApiResponse<Boolean> existsPermissionByName(@PathVariable String name) {
    return ApiResponse.<Boolean>builder()
        .data(permissionService.existsByName(name))
        .status(1)
        .message("<Exists Permission Successfully>")
        .build();
  }

  @GetMapping("/findByDescription/{description}")
  ApiResponse<PermissionResponse> findPermissionByDescription(@PathVariable String description) {
    return ApiResponse.<PermissionResponse>builder()
        .data(permissionService.findByDescription(description))
        .status(1)
        .message("<Find Permission By Description Successfully>")
        .build();
  }

  @PutMapping("/update/{name}")
  ApiResponse<PermissionResponse> updatePermission(
      @PathVariable String name, @RequestBody @Valid PermissionRequest permissionRequest) {
    return ApiResponse.<PermissionResponse>builder()
        .data(permissionService.updatePermissionByName(name, permissionRequest))
        .status(1)
        .message("<Update Permission Successfully>")
        .build();
  }

  @Transactional
  @DeleteMapping("delete/{name}")
  public ApiResponse<String> deletePermissionByName(@PathVariable String name) {
    permissionService.deletePermissionByName(name);
    return ApiResponse.<String>builder()
        .status(1)
        .message("<Delete Permission Successfully>")
        .data("OK")
        .build();
  }

  @GetMapping("/keyword/{keyword}")
  ApiResponse<List<PermissionResponse>> findByKeyword(@PathVariable String keyword) {
    return ApiResponse.<List<PermissionResponse>>builder()
        .status(1)
        .message("<Find Permission By Keyword Successfully>")
        .data(permissionService.searchByKeyword(keyword))
        .build();
  }
}
