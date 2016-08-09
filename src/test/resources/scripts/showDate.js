function showDate(timestamp) {
	print('Jahr:  ' + timestamp.get(java.util.Calendar.YEAR))
	print('Monat: ' + (timestamp.get(java.util.Calendar.MONTH)+1))
	print('Tag:   ' + timestamp.get(java.util.Calendar.DAY_OF_MONTH))
}