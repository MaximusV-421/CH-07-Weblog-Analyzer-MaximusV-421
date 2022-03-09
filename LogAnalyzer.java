/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Maximus Vancaneghem.
 * @version 2022.03.08
 */
public class LogAnalyzer
{ 
    
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    private int[] yearCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demoLog.txt");
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * @return total amount of times accessed.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to 
        // total.
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        reader = new LogfileReader("demoLog.txt");
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the monthly access data from log file.
     */
    public void averageAccessesPerMonth()
    {
        reader = new LogfileReader("demoLog.txt");
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Return the busiest hour by analyzing the file.
     * @return busiest hour within the file.
     */
    public int busiestHour()
    {
        int busiestAccesses = 0;
        int busiestHour = 0;
        int index = 0;
        
        while(index < hourCounts.length -1)
        {
            if(busiestAccesses < hourCounts[index])
            {
                busiestHour = index;
                busiestAccesses = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return busiestHour;
    }
    
    /**
     * return the quietest hour by analyzing the file.
     * @return the quietest hour within the file.
     */
    public int quietestHour()
    {
        int quietAccesses = 9999;
        int quietestHour = 0;
        int index = 0;
        
        while(index < hourCounts.length -1)
        {
            if(quietAccesses > hourCounts[index])
            {
                quietestHour = index;
                quietAccesses = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return quietestHour;
    }
  
    /**
     * Return which two-hour period is the busiest by analyzing the file.
     * @return the busiest two hour period within the file.
     */
    public int busiestTwoHour()
    {
        int busiestAccesses = 0;
        int index = 0;
        int busiestHour = 0;
        
        while(index < hourCounts.length - 1){
            if (busiestAccesses < hourCounts[index] + hourCounts[index + 1]){
                busiestHour = index;
                busiestAccesses = hourCounts[index] + hourCounts[index + 1];
                index++;
            }
            else {
                index++;
            }
        }
        return busiestHour;
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the quietest day by analyzing the file.
     * @return quiestest day within the file.
     */
    public int quietestDay()
    {
        int dayNUM = 1;
        int quietest = dayCounts[dayNUM];
        int quietestDay = 50;
        for(int day = 1; day < dayCounts.length;day++)
        {
            if (quietest > dayCounts[day])
            {
                quietest = dayCounts[day];
                quietestDay = day;
            }
        }
        if(quietestDay == 0)
        {
            quietestDay = 1;
        }
        return quietestDay;
    }
    
    /**
     * Return the busiest day by analyzing the file.
     * @return the busiest day within the file.
     */
    public int busiestDay()
    {
        int busiest = 0;
        int busiestDay = 50;
        for(int day = 1; day < dayCounts.length;day++)
        {
            if (busiest > dayCounts[day])
            {
                busiest = dayCounts[day];
                busiestDay = day;
            }
        }
        return busiestDay;
    }
    
    /**
     * Return the total amount of accesses per month.
     * @return total accessses per month.
     */
    public int totalAccessesPerMonth()
    {
        int total = 0;
        int month = 1;
        while (month < monthCounts.length)
        {
            total += monthCounts[month];
            month++;
        }
        return total;
    }
    
    /**
     * Return the quietest month.
     * @return quietest month
     */
    public void quietestMonth()
    {
        for(int year = 0; year < yearCounts.length;year++)
        {
            int monthNUM = 1;
            int quietest = monthCounts[monthNUM];
            int quietestMonth = 50;
            for(int month = 1; month < monthCounts.length;month++)
            {
                if(quietest > monthCounts[month])
                {
                    quietest = monthCounts[month];
                    quietestMonth = month;
                }
            }
            if(quietestMonth == 50)
            {
                quietestMonth = 1;
            }
            System.out.println("Year: " + (2015 +year) + " Quietest Month: " + quietestMonth);
        }
    }
    
    /**
     * Return the busiest month.
     * @return busiest month
     */
    public void busiestMonth()
    {
        for(int year = 0; year < yearCounts.length;year++)
        {
            int busiest = 0;
            int busiestMonth = 50;
            for(int month = 0; month < monthCounts.length;month++)
            {
                if(busiest > monthCounts[month])
                {
                    busiest = monthCounts[month];
                    busiestMonth = month;
                }
            }
            System.out.println("Year: " + (2015 +year) + " Busiest Month: " + busiestMonth);
        }
    }
}
