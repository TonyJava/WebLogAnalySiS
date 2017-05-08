# WebLogAnalySiS
  Web log Analysis project using MapReduce,hive,pig,sqoop,oozie

##Input Dataset:
Input dataset is a Web Server log which has the following details:

  IP Address,
  Timestamp,
  Request Type,
  Resource Requested,
  Request Status,
  Referrer URL, Search key words 
  User browser
  
##Requirement:
- Find the Top 10 search key words used in the search engines for each year / month
- Find the Top 5 websites which got the maximum number of hits for each month (including robots)

##Steps Involved:
- Extract required columns from each URL usingJavaMap Reduce
- Create a custom Writable for WebLog Record
- Create a customRecord Reader which will read the input records and emit 
    key = IP Address 
    value = WebLogWritable
- Filter out records not having URL or keywords in the mapper
- Save the output of the mapper to JSON files.
- Create a staging external table in Hive to read the JSON files 
- Create partitioned table (partition by year / month) and load data from staging table
- Use Hive queries to list Top 10 key words for each year / month
- Use Oozie workflow to automate the process 
