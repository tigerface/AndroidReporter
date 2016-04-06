# AndroidReporter
a easy library for Android report.

#Feature
* auto log Activity&Fragment lifecycle to file
* support push a single report right now, this report is logged in file of course.
* custome report
* single 2 threads to log report event,not block UI thread



# Report log file demo
/data/data/yourpackage/files/2016-04-06 18.log
>>
{"type":1,"timeStamp":1459925831393,"mMethod":0,"tag":"MainActivity","extra":"onCreate","mUrl":""}
{"type":1,"timeStamp":1459925831426,"mMethod":0,"tag":"MainActivity","extra":"onStart","mUrl":""}
{"type":1,"timeStamp":1459925831426,"mMethod":0,"tag":"MainActivity","extra":"onResume","mUrl":""}
{"type":1,"timeStamp":1459925834527,"mMethod":0,"tag":"MainActivity","extra":"我点击了按钮","mUrl":""}
{"type":1,"timeStamp":1459925834532,"mMethod":0,"tag":"MainActivity","extra":"onPause","mUrl":""}
{"type":1,"timeStamp":1459925834543,"mMethod":0,"tag":"SecondActivity","extra":"onCreate","mUrl":""}
{"type":1,"timeStamp":1459925834547,"mMethod":0,"tag":"SecondActivity","extra":"onStart","mUrl":""}
{"type":1,"timeStamp":1459925834547,"mMethod":0,"tag":"SecondActivity","extra":"onResume","mUrl":""}
{"type":1,"timeStamp":1459925834947,"mMethod":0,"tag":"MainActivity","extra":"onStop","mUrl":""}
{"type":1,"timeStamp":1459925835839,"mMethod":0,"tag":"SecondActivity","extra":"onPause","mUrl":""}
{"type":1,"timeStamp":1459925836708,"mMethod":0,"tag":"SecondActivity","extra":"onStop","mUrl":""}

# Target
We can use the log file to anaylsis:
  * the whole time of stay every Activity or Fragment
  * log where every single custom report is from
# Thanks
Thanks for Volly,when I began to write this first Android library,my inspiration is from volly.
