package pl.myworkspace.reportingapp.entity.report;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StandardReport extends Report{



    private String installedDevice; //TODO change to InstalledDevice installedDevice




}
