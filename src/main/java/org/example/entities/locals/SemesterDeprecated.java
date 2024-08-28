package org.example.entities.locals;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.BaseEntity;
import org.example.entities.loan.StudyLoan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Deprecated
public class SemesterDeprecated extends BaseEntity<Long> {

      //  private Date date;
        private int year;
        private int semesterNumber;

        @ManyToOne
        private StudyLoan studyLoan;



        public SemesterDeprecated(Date date) {
            // Convert Date to LocalDate
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Assign year
             this.year= localDate.getYear();

            // Determine semester number based on month
            int month = localDate.getMonthValue(); // January = 1, December = 12
            if (month >= 1 && month <= 6) {
                this.semesterNumber = 1; // Winter Semester
            } else {
                this.semesterNumber = 2; // Summer Semester
            }
        }


}
