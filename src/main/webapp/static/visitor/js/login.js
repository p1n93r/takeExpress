var timer;
function showfh(){
    fhi = 1;
    //关闭提示晃动屏幕，注释掉这句话即可
    timer = setInterval(xzfh2, 10);
};
var current = 0;
function xzfh(){
    current = (current)%360;
    document.body.style.transform = 'rotate('+current+'deg)';
    current ++;
    if(current>360){current = 0;}
};
var fhi = 1;
var current2 = 1;
function xzfh2(){
    if(fhi>50){
        document.body.style.transform = 'rotate(0deg)';
        clearInterval(timer);
        return;
    }
    current = (current2)%360;
    document.body.style.transform = 'rotate('+current+'deg)';
    current ++;
    if(current2 == 1){current2 = -1;}else{current2 = 1;}
    fhi++;
};


