**Title:**
*JACKPOT KING*

**Subtitle:**
*Prediction of Lottery Numbers*

**Who will use it?**
This application will mainly focus on analytical prediction to lottery games involve numbers. 
The applicable lotteries is LottoMax.
There are countless amount of people not only in Canada but also Worldwide, 
buying lotteries. Some of them are just having lottery fevers just for a while,
whereas there are also some people having strong sense of wining and regarding lotteries as a long-term investment. 
This application is built especially for these people.

**Why i choose this?**
The main reason I choose this as my project is due to that I am also a lottery enthusiast,
since it does not require a lot of time, money, and skills. You might think that everyone can be able to play this, 
and lottery draws are based on random pick, what pushes me to do this useless application? Well, I did some research 
and compared nearly a thousand group of numbers, came out with one solution that is there exists strategies that can 
help you wining. Even though the rate of wining some crazy million dollars are still low, at least it can prevent you 
from spending money for nothing, or at the very least it can make you some entertainments lol.

**User Stories**
As a user, i want to be able to view the lottery history.
As a user, i want to be able to see the statistics of lotteries.
As a user, i want to be able to get numbers from prediction.
As a user, i want to be able to add my own numbers or random numbers to simulator.
As a user, i want to be able to save my to do list to file.
As a user, i want to be able to open my file any time when program runs.

**Instructions for Grader**
First, If you get into the ui package, you will see there is a class called JackPOtKingGUI, run it and it will display
 a menu window with different functionality. For the "ADD X TO Y" task you can achieve it by clicking the input blank 
 and entering 7 different numbers below 50. Then you can click the tasks below to see the effect as if you input 7 
 numbers the click addNewRecord, it will add your number to the history class. Also before you do prediction and 
 simulation you also need to input first then click the button. For the loadHistory and viewStat task, they do not 
 require a input but also no new windows will come out due to that it has already been there at the left.
 You can certainly here a bell-like sound when you click and you will notice there is a logo at the top right. 
 The website where i get it is: https://www.playolg.ca/content/dam/olg/responsive/new-lottomax/images/lotto-max.jpg
 
Due to that some of my tasks are based on random generated numbers so its hard to get perfect on autobot score!!! 
 There is a TA who knows my situation and hopefully he will grade my phase 3! :)
 
 **Phase4 Task2**
 For this task i chose the robust design. The class i modified is LottoMax, and the method i modified is the ParseNo().
 This method previously requires that user need to input a well formatted group of numbers, otherwise the error code
 will come out. However, after this robust design, there would not be any red errors coming out when you do addNewRecord
 or simulation instead it will notify users when their input are not corrected. I also made test for it. Have a look in
 the LottoMaxTest testParseNo and further.
 
 **Phase4 Task3**
 Previously in my LottoMax class, there are different places that requires different numbers of index such as 51 and 7.
 51 means the most recent 51 group of numbers in the history files while 7 means how many number consist of one group.
 They appear in different methods thus their functionality differ. Changing one place may lead to errors which in 
 other words too much coupling. So i decided to factor out these numbers and make them static final at the top (Recent50
 ,TotalNum). By doing this i would gain benefits that coupling are well balanced and system are stable.
 
 On the other hand, on the past phases i put the record stuff in LottoMax class which does not really match its 
 characteristics. So i did pull that out and making it a different class only doing its record job. By doing this,
 cohesion is defended and making the classes distinct to each other.
 
 The UML Diagram has been included in this project - "IMG 5008". Due to that some of my methods are depending on
 random numbers. So please show mercy when you see my code grade is 93.6%. Thanks!
 

