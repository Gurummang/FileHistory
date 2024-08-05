package com.GASB.file.service.dashboard;

import com.GASB.file.model.dto.response.dashboard.FileDashboardDto;
import com.GASB.file.model.dto.response.dashboard.StatisticsDto;
import com.GASB.file.model.dto.response.dashboard.TotalTypeDto;
import com.GASB.file.repository.file.FileUploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileBoardReturnService {

    private final FileUploadRepo fileUploadRepo;

    @Autowired
    public FileBoardReturnService(FileUploadRepo fileUploadRepo){
        this.fileUploadRepo = fileUploadRepo;
    }

    public FileDashboardDto boardListReturn(long org_saas_id){
        int totalCount = totalFilesCount(org_saas_id);
        double totalVolume = totalFileSizeCount(org_saas_id);
        int totalDlp = totalDlpCount(org_saas_id);
        int totalMalware = totalMalwareCount(org_saas_id);

        List<TotalTypeDto> totalType = List.of(
                new TotalTypeDto("Type1", 50),
                new TotalTypeDto("Type2", 50)
        );

        List<StatisticsDto> statistics = List.of(
                new StatisticsDto("2024-01-01", 2.3f, 30),
                new StatisticsDto("2024-01-02", 4.0f, 24)
        );

        // FileDashboardDto 객체를 생성하고 반환
        return FileDashboardDto.builder()
                .total_count(totalCount)
                .total_volume(totalVolume)
                .total_dlp(totalDlp)
                .total_malware(totalMalware)
                .total_type(totalType)
                .statistics(statistics)
                .build();
    }

    private int totalFilesCount(long org_saas_id){
        return fileUploadRepo.countByOrgSaasId(org_saas_id);
    }

    private double totalFileSizeCount(long org_saas_id){
        double totalSizeInBytes = fileUploadRepo.sumFileSizeByOrgSaasId(org_saas_id);
        double totalSizeInGB = totalSizeInBytes / 1_073_741_824.0;
        return Math.round(totalSizeInGB * 1000) / 1000.0;
    }

    private int totalDlpCount(long org_saas_id){
        return fileUploadRepo.countDlpReportsByOrgSaasId(org_saas_id);
    }

    private int totalMalwareCount(long org_saas_id){
        return fileUploadRepo.countVtReportsByOrgSaasId(org_saas_id);
    }
}
