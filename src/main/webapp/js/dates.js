/*12hours format*/

function GetClock(){
var d=new Date();
var nhour=d.getHours(),nmin=d.getMinutes(),nsec=d.getSeconds(),ap;


if(nhour==0){ap=" AM";nhour=12;}
else if(nhour<12){ap=" AM";}
else if(nhour==12){ap=" PM";}
else if(nhour>12){ap=" PM";nhour-=12;}

if(nmin<=9) nmin="0"+nmin;
if(nsec<=9) nsec="0"+nsec;


document.getElementById('clockbox').innerHTML=""+nhour+":"+nmin+":"+nsec+ap+"";
}

window.onload=function(){
GetClock();
setInterval(GetClock,1000);
}


/*24hours format*/

function GetClock(){
	var d=new Date();
	var day=d.getDay();
	var dates = d.getDate();
	var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
	var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
	var nhour=d.getHours(),nmin=d.getMinutes(),nsec=d.getSeconds();
	
	
	if(nmin<=9) nmin="0"+nmin;
	if(nsec<=9) nsec="0"+nsec;
	if(nhour<=9) 
		{
		nhour='0'+nhour;
		}
	if(dates==1 || dates==21 || dates==31)
		{
		dates=dates+'st';
		}
	else if(dates==2 || dates==22)
		{
		dates=dates+'nd';
		}
	else if(dates==3 || dates==23)
		{
		dates=dates+'rd';
		}
	else 
		{
		dates=dates+'th';
		}
	
	
	document.getElementById('dates').innerHTML=dates;
	document.getElementById('years').innerHTML=new Date().getFullYear();
	document.getElementById('days').innerHTML=dayarray[day];
	document.getElementById('months').innerHTML=months[d.getMonth()];
	document.getElementById('clockbox1').innerHTML=""+nhour+":"+nmin+":"+nsec+"";
	}

	window.onload=function(){
	GetClock();
	setInterval(GetClock,1000);
	}