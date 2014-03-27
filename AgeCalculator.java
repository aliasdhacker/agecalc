/**
 * Java age calculator.
 * 
 * This application is a demonstration of some basic Java functionality.  
 *
 * To figure out where the Calendar class was, ie. java.util.Calendar, I used
 * http://docs.oracle.com/javase/7/docs/api/, which is the java API reference.
 * This reference lists all of the classes in the Java SDK, and it will help you
 * learn where things are, and what you can do with them.   If you use an IDE
 * to start programming, you normally miss out on this step, as the idea will
 * auto complete the text for you.  While this is much easier to do, and you 
 * can produce products much, much faster, you rob something from the guy just 
 * starting programming, the ability to program independently of an IDE.
 *
 * Please let me know any questions you have, I will be happy to help!
 * 
 * @author aliasdhacker
 * 
 */
public class AgeCalculator {
    /**
     * This is an age calculator.  Pass in a millisecond value of a date in the past, as arg[0].
	 *
	 * It cuts off at days, and does not carry forward previous partial values, but truncates them instead.
	 *
	 * This app could be expanded on easily, it is meant to be any easy start.
	 *
	 * To pass in arg[0], run the app as follows:
	 *  "java AgeCalculator 86400000" 
	 *  which should output:
	 * 		/dev/Open_Source_Projects\agecalc>java AgeCalculator 86400000
	 *		Aged 1 days.
     */
    public static void main(String[] args) {
		String age = "";
		// Instead of using java.util.Calendar below, I could have added
		// the import statement "import java.util.Calendar;" at the beginning
		// of my class, and then referred to Calendar as "Calendar".
        java.util.Calendar date = java.util.Calendar.getInstance();
		Float x = Float.valueOf(args[0]);
		date.setTimeInMillis(System.currentTimeMillis() - x.longValue());

        if (x < 60000) {
            // return "< 1 Minute";
            age = " <1 Minute";
        } else {
			x /= 1000;  // For milliseconds removal
			float secondsIgnore = x % 60; //Total seconds.
			x-= secondsIgnore;  // Removal fractional minutes from equation. 
			x = x / 60; // Find total minutes. 
			
			// We could probably do the last 3 lines in one line, like:
			// x=(x -= (x%60))/60;
			// But the problem is self commenting / readable code.  This statement
			// is much less readable than the previous three.  This code is not meant
			// to be self commenting, as this is for beginners to learn.
			// See: http://en.wikipedia.org/wiki/Self-documenting for a start on learning
			// self commenting code.
			
			Float min = x%60;  // Find remainder of minutes, inside an hour.
			x = x-min;// Remove partial hour.
			age = min.intValue()+ " minutes";
			if(x>0) { // If the total minutes minus partial hour is less than 1 (meaning the total time was less than 60 minutes), skip calculating hours/days/etc.
				x /= 60; // total hours
				Float hour = x % 24; // Find the remainder of hours that are left after dividing by 24.
				x-=hour;
				age=hour.intValue() + " hours";
				if(x>0){
					Float days=x/24; // total days. largest value, stop here.
					age = days.intValue() + " days";
				}
			}
	    }
		
		System.out.println("You entered a date of " + date.getTime() + " which occurred " + age + " ago.");
    }
}