package dates;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDateTime anoPassado = LocalDateTime.now().minusYears(1);
        System.out.println(anoPassado);

        LocalDateTime hojeMeioDia = LocalDate.now().atTime(12, 0, 3);
        System.out.println(hojeMeioDia);

        LocalDate hoje = LocalDate.now();
        LocalTime meioDia = LocalTime.of(12, 0, 3);

        System.out.println(hoje.atTime(meioDia).equals(hojeMeioDia));

        ZonedDateTime hojeMeioDiaSaoPaulo = hojeMeioDia.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println(hojeMeioDiaSaoPaulo);
        System.out.println(hojeMeioDia.equals(hojeMeioDiaSaoPaulo));

        LocalDateTime antigo = LocalDateTime.now().withYear(1943).withDayOfMonth(1).withMonth(1);
        System.out.println(antigo);


        ZonedDateTime agoraSaoPaulo = ZonedDateTime.of(2017, 2, 28, 20, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime agoraTokyo = ZonedDateTime.of(2017, 2, 28, 20, 30, 0, 0, ZoneId.of("Asia/Tokyo"));

        System.out.println(agoraSaoPaulo.equals(agoraTokyo));
        System.out.println(agoraSaoPaulo.isEqual(agoraTokyo));
        agoraTokyo = agoraTokyo.plusHours(12);
        System.out.println(agoraSaoPaulo.equals(agoraTokyo));
        System.out.println(agoraSaoPaulo.isEqual(agoraTokyo));

        System.out.println(agoraSaoPaulo);
        System.out.println(agoraTokyo);


        System.out.println(MonthDay.now().getDayOfMonth());
        System.out.println(Year.now());
        System.out.println(DayOfWeek.of(7));


        System.out.println(LocalDate.of(2018, Month.JULY, 1));
        System.out.println(Month.JULY.firstMonthOfQuarter());


        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.NARROW, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

        System.out.println(anoPassado);
        System.out.println(anoPassado.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(anoPassado.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(anoPassado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));

        System.out.println(ChronoUnit.DAYS.between(anoPassado, LocalDateTime.now()));
        System.out.println(ChronoUnit.DAYS.between(LocalDate.now().withDayOfMonth(1), LocalDate.now()));
        System.out.println(ChronoUnit.MONTHS.between(LocalDate.now().withDayOfMonth(1), LocalDate.now()));
        System.out.println(ChronoUnit.YEARS.between(LocalDate.now().withYear(1900), LocalDate.now()));

        Period periodoCopas = Period.between(LocalDate.of(2014, 8, 10), LocalDate.of(2018, 7, 19));
        System.out.println(periodoCopas.toTotalMonths());
        System.out.println(periodoCopas.getYears());
        System.out.println(periodoCopas.getMonths());
        System.out.println(periodoCopas.getDays());
    }
}
