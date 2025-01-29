package com.vang.main.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: CreateFolderReq
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateFolderReq {

    @Schema(name = "folderName", description = "Folder name", example = "VANG2002", required = true)
    @NotNull
    private String folderName;
}