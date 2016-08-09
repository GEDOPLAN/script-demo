import java.util.Calendar;

def showDate(timestamp) {
  System.out.println("Jahr:  " + timestamp.get(Calendar.YEAR))
  System.out.println("Monat: " + timestamp.get(Calendar.MONTH))
  System.out.println("Tag:   " + timestamp.get(Calendar.DAY_OF_MONTH))
}
