//Campbell Schweickhardt
// Code that wil run when page is loaded
if( GetCookie('user_id') == null ){
    var getName = prompt("Hi! Enter your name and you can choose an NFL team from the AFL!",'');
    //validates that a user entered their name
    if (getName == null || getName == ''){
        alert("We just want you name, don't be shy!")
        var getName = prompt("Hi! Enter your name and you can choose an NFL team from the AFL!",'');
    }
    document.write('<h2>Welcome, '+getName+'! Time to choose an AFL team!</h2>');
    // Save the information in a cookie
    SetCookie('user_id', getName);
    SetCookie('hit_count', '1' );
}
else	// welcome back
{
    var getName = GetCookie('user_id');
    var getHits = GetCookie('hit_count');
    getHits = parseInt( getHits ) + 1;
    document.write('<h2>Welcome back '+getName+' you have visited '+getHits+' times, who will you choose this time?</h2>');
    // store the new hit count
    SetCookie('hit_count', getHits);
}
mySelect = 1;//depth of question
//this function is used to dynamically create the chooices a user will go threw to choose something, in this case an AFC team
function createSelect(which, num){//whitch is the answer of the last question, num is the dapth 
    var inData = data[which];//sets the array being used from the answer given
    if(num < 5){//depth of five is the quesion that gives user selection 
        isAnswer()//checks to see if the user has come to an answer to see if it needs to be removed
        while (num < mySelect){//if the returned depth is < overall depth
            document.getElementById(mySelect).remove();//questions answers needs to be removed
            mySelect--;//lowers depth
        }
        mySelect++;//raised depth
        var select = document.createElement("SELECT");//creates the html select
        select.setAttribute("id", mySelect);//add id so it can be referanced later
        select.setAttribute("onchange", "createSelect(value, this.id)");//adds oncnage, so when the quetion is answered it will get the next question ready
        document.body.appendChild(select); //adds to webpage

        var question = document.createElement("option");//creates the first option for select, this is the question
        question.setAttribute("value", inData[0]);//sets value to qestion
        var questionText = document.createTextNode(inData[0]);//text set to the question
        question.appendChild(questionText);//appends question to option
        document.getElementById(mySelect).appendChild(question);//adds to webpage
        
        var answerOne = document.createElement("option");//creates the second option for select, this is the first choice
        answerOne.setAttribute("value", inData[1]);//sets value to frist choice
        var answerOneText = document.createTextNode(inData[1]);//text set to the first choice
        answerOne.appendChild(answerOneText);//appends first choice to option
        document.getElementById(mySelect).appendChild(answerOne);//adds to webpage

        var answerTwo = document.createElement("option");//creates the third option for select, this is the second choice
        answerTwo.setAttribute("value", inData[2]);//sets value to secon choice
        var answerTwoText = document.createTextNode(inData[2]);//text set to the secon choice
        answerTwo.appendChild(answerTwoText);//appends second choice to option
        document.getElementById(mySelect).appendChild(answerTwo);//adds to webpage
    }
    else{
        isAnswer()//checks to see if the user has come to an answer to see if it needs to be removed
        var hOne = document.createElement("H1");//creates H1 tage
        hOne.setAttribute("id", "Answer");//sets id so i can be acsesed later
        var choice = document.createTextNode(inData[0]);//created the choice text
        hOne.appendChild(choice);//adds the choice text to H1
        document.body.appendChild(hOne);//adds to webpage
    }
} //end createText
//checks to see if the user has come to an answer to see if it needs to be removed
function isAnswer() {
    if(document.getElementById("Answer") != null){//id the id exists
        document.getElementById("Answer").remove();//get rid of it
    }
}