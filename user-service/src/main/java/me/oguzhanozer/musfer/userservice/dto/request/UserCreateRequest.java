package me.oguzhanozer.musfer.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
                @NotBlank @Email String email,

                @NotBlank @Size(min = 3, max = 30) @Pattern(regexp = "^[A-Za-z0-9]+(?:[-_.][A-Za-z0-9]+)*$") String username) {
}
