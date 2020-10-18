function changeCode() {
    let codeImg = document.getElementById("VCode");
    codeImg.src="/CreateVerifyImageController?t="+Math.random();
}

let xmlHttp;

//创建XMLHttpRequest对象

function creatXmlHttp() {
    if (window.XMLHttpRequest) {
        xmlHttp=new XMLHttpRequest(); //支持所有现代浏览器
    }else{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); //IE5 IE6需要ActiveObject()构造函数
    }
}

//使用原生的js实现ajax登录

function ajaxCheckLogin() {

    //取表单数据
    let userName=document.getElementById("userName").value;
    let passWord=document.getElementById("passWord").value;
    let userCode=document.getElementById("userCode").value;
    let checkBox=document.getElementById("checkbox").value;

    //调用自定义函数创建XMLHttpRequest对象
    creatXmlHttp();
    xmlHttp.open("post","controller/ajaxLoginCheck",true);
    xmlHttp.setRequestHeader("Context-type","application/x-www-form-urlencoded");
    xmlHttp.send("userName="+userName+"&passWord="+passWord+"&userCode="+
                userCode+"&checkBox="+checkBox);

    //接受服务器返回及处理
    xmlHttp.onreadystatechange = function () { //回调函数
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            let response=xmlHttp.responseText;
            let json=JSON.parse(response);
            if (json.code === 0) {//登录成功
                window.location.href="main.jsp";
            }else{//登录失败
                //显示返回错误信息
                document.getElementById("checkError").innerText=json.info;
            }
        }

    }
}