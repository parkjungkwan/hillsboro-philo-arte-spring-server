package hillsboro.philoarte.scalar.types;

import hillsboro.philoarte.scalar.entities.ResumeFile;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeFileDto {

    private Long resumeFileId;
    private String uuid;
    private String fname;
    private Boolean repImg;
    private String fileTitle;
    private String fileDetail;
    private String fileWorkedDate;

    public static ResumeFileDto of(ResumeFile resumeFile) {
        ResumeFileDto resumeFileDto = ModelMapperUtils.getModelMapper().map(resumeFile, ResumeFileDto.class);
        return resumeFileDto;
    }

}
