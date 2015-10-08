//Animazione delle porte
portAnimation1=function(port){
var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:Math.PI/2}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();}

portAnimation2=function(port){
	var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:0}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();}

portAnimation3=function(port){
	 var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:-Math.PI/2}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();
}

portAnimation4=function(port){
	var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:0}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();
}

portAnimation5=function(port){
	var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:Math.PI}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();
}

portAnimation6=function(port){
	 var portTween1 = new TWEEN.Tween(port.rotation)
            .to({z:Math.PI/2}, 1100)
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();
}

portAnimation7=function(port){
	var portTween2 = new TWEEN.Tween(port.scale)
            .to({x:port.PortdimX/2}, 800)
            .easing(TWEEN.Easing.Quadratic.InOut); 
     
       var portTween3 = new TWEEN.Tween(port.position)
        .to({y:port.PortPosY-(port.PortdimX-0.1)}, 800)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            

      var portTween1 = new TWEEN.Tween(port.position)
            .chain(portTween2,portTween3)       
            .start();   
}

portAnimation8=function(port){
	var portTween2 = new TWEEN.Tween(port.scale)
            .to({x:port.PortdimX+0.1}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut); 

            var portTween3 = new TWEEN.Tween(port.position)
            .to({y:port.PortPosY}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween(port.position)
            
            .chain(portTween2,portTween3)            
            .start();
}

portAnimation9=function(port){
	 var portTween2 = new TWEEN.Tween(port.scale)
            .to({x:port.PortdimX/2}, 800)
            .easing(TWEEN.Easing.Quadratic.InOut); 
     
       var portTween3 = new TWEEN.Tween(port.position)
        .to({x:(port.PortPosX+(port.PortdimX/2))+(port.PortdimX-0.1)}, 800)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            

      var portTween1 = new TWEEN.Tween(port.position)
            .chain(portTween2,portTween3)       
            .start();
}

portAnimation10=function(port){

            var portTween2 = new TWEEN.Tween(port.scale)
            .to({x:port.PortdimX+0.2}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut); 

            var portTween3 = new TWEEN.Tween(port.position)
            .to({x:port.PortPosX+(port.PortdimX/2)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween(port.position)
            
            .chain(portTween2,portTween3)            
            .start();
}

//Animazione dei finestroni
finestroneAnimation1=function(Finestrone2){

            var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-(Finestrone2.DimX-(Finestrone2.DimX/4))}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.15}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation2=function(Finestrone2){
       var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.03}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-((Finestrone2.DimX/4)+0.025)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation3=function(Finestrone2){
        var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-(((Finestrone2.DimX/4)+0.025)+(Finestrone2.DimX*2))}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.15}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation4=function(Finestrone2){      
               var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.03}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-(Finestrone2.DimX*1.8)+0.02}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation5=function(Finestrone2){
         var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-(Finestrone2.DimX+0.2)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.15}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation6=function(Finestrone2){
          var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.03}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-((Finestrone2.DimX/2)+0.25)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation7=function(Finestrone2){
        var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-(Finestrone2.DimX+0.15)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut) ;  

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.15}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

finestroneAnimation8=function(Finestrone2){
       var portTween2 = new TWEEN.Tween( Finestrone2.position)
            .to({z:Finestrone2.PosY-0.03}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut);   

            var portTween1 = new TWEEN.Tween( Finestrone2.position)
            .to({x:Finestrone2.PosX-((Finestrone2.DimX/2)+0.15)}, 600)
            .easing(TWEEN.Easing.Quadratic.InOut)   
            .chain(portTween2)            
            .start();
}

//Animazione della pioggia: Lavora sulle luci e sui colori
function rainAnimation(){

var rainTween3=new TWEEN.Tween(base.material.color)
        .to({r: 0.5,g: 0.5,b: 0.5}, 1000);

var rainTween4 = new TWEEN.Tween(paesaggio.color)
        .to({r: 0.6,g: 0.6,b: 0.12},1000);

//var rainTween5 = new TWEEN.Tween(DirectionalLight3)
//.to({intensity: 0.4}, 1000);

var rainTween2 = new TWEEN.Tween(DirectionalLight1)
.to({intensity: 0.4}, 1000);


var rainTween1 = new TWEEN.Tween(DirectionalLight2)
.to({intensity: 0.4}, 1000)
.chain(rainTween2,rainTween3)  
.start();}

function rainAnimation2(){
var rainTween3=new TWEEN.Tween(base.material.color)
        .to({r: 1,g: 1,b:1}, 1000);


var rainTween4 = new TWEEN.Tween(paesaggio.color)
        .to({r: 1,g: 1,b: 1},1000);

//var rainTween5 = new TWEEN.Tween(DirectionalLight3)
//.to({intensity: 1.3}, 1000);

var rainTween2 = new TWEEN.Tween(DirectionalLight1)
.to({intensity: 1.3}, 1000);


var rainTween3 = new TWEEN.Tween(DirectionalLight2)
.to({intensity: 1.3}, 1000)
.chain(rainTween2,rainTween3)  
.start();}

//Animazione della doccia.
function showerAnimation(){
var showerTween1 = new TWEEN.Tween(pomelloDoccia.rotation)
.to({y: -Math.PI/2}, 700)
.start();}


function showerAnimation2(){
var showerTween1 = new TWEEN.Tween(pomelloDoccia.rotation)
.to({y: 0}, 700)
.start();}


//Animazione sulle luci della casa
function lightAnimation(){
if(rain==true){
      for(i=0;i<HomeSpotLight.length;i++){
           if(i!=1){ 

var lightTween3 = new TWEEN.Tween(HomeSpotLight[i])
.to({intensity: 8}, 1000);

var lightTween2 = new TWEEN.Tween(HomePointLight[i])
.to({intensity: 0.33}, 1000)

var lightTween1 = new TWEEN.Tween()
.chain(lightTween2,lightTween3)
.start();}}}
            
else if(rain==false){
      for(i=0;i<HomeSpotLight.length;i++){
            if(i!=1){
          var lightTween3 = new TWEEN.Tween(HomeSpotLight[i])
.to({intensity: 0}, 1000);

var lightTween2 = new TWEEN.Tween(HomePointLight[i])
.to({intensity: 0}, 1000)

var lightTween1 = new TWEEN.Tween()
.chain(lightTween2,lightTween3)
.start();}}}

}

//Animazione per la luce della camera
function Camera01lightAnimation(){
if(lightCamera01==false){
  lightCamera01=true;

  var lightTween1 = new TWEEN.Tween(bottoneLight.bottoneLight.rotation)
  .to({x: Math.PI/4}, 500)
  .easing(TWEEN.Easing.Quadratic.InOut)
  .start();

  HomePointLight[1].intensity=0.3;
  HomeSpotLight[1].intensity=8;
  click.play();}

else if(lightCamera01==true){
  lightCamera01=false;
  HomePointLight[1].intensity=0;
  HomeSpotLight[1].intensity=0;
    
  var lightTween1 = new TWEEN.Tween(bottoneLight.bottoneLight.rotation)
 .to({x: -Math.PI/4}, 500)
 .easing(TWEEN.Easing.Quadratic.InOut)
 .start();
  click.play();}}

//Animazione della notte. Lavora sulle luci
function nightAnimation1(){
for(i=0;i<HomeSpotLight.length;i++){
           if(i!=1){ 

var nightTween3 = new TWEEN.Tween(HomeSpotLight[i])
.to({intensity: 8}, 1000);

var nightTween2 = new TWEEN.Tween(HomePointLight[i])
.to({intensity: 0.33}, 1000)

var nightTween1 = new TWEEN.Tween()
.chain(nightTween2,nightTween3)
.start();}}

var nightTween9=new TWEEN.Tween(base_Innevata.material.color)
        .to({r: 0.3,g: 0.3,b: 0.3}, 1000);


var nightTween8=new TWEEN.Tween(base.material.color)
        .to({r: 0.3,g: 0.3,b: 0.3}, 1000);

//var nightTween7 = new TWEEN.Tween(paesaggio.color)
//        .to({r: 0.2,g: 0.2,b: 0.4},1000);

//var nightTween10 = new TWEEN.Tween(DirectionalLight3)
//.to({intensity: 0.1}, 1000);

var nightTween5 = new TWEEN.Tween(DirectionalLight1)
.to({intensity: 0.1}, 1000);

var nightTween4 = new TWEEN.Tween(DirectionalLight2)
.to({intensity: 0.1}, 1000)

var nightTween6 = new TWEEN.Tween()
.chain(nightTween4,nightTween5,nightTween8,nightTween9)
.start();

}

function nightAnimation2(){             
      for(i=0;i<HomeSpotLight.length;i++){
            if(i!=1){
            var lightTween3 = new TWEEN.Tween(HomeSpotLight[i])
.to({intensity: 0}, 1000);

var lightTween2 = new TWEEN.Tween(HomePointLight[i])
.to({intensity: 0}, 1000)

var lightTween1 = new TWEEN.Tween()
.chain(lightTween2,lightTween3)
.start();}}

var nightTween9=new TWEEN.Tween(base_Innevata.material.color)
        .to({r: 1,g: 1,b: 1}, 1000);

var nightTween8=new TWEEN.Tween(base.material.color)
        .to({r: 1,g: 1,b: 1}, 1000);

var nightTween7 = new TWEEN.Tween(paesaggio.color)
        .to({r:1,g: 1,b: 1},1000);

//var nightTween10 = new TWEEN.Tween(DirectionalLight3)
//.to({intensity: 1.3}, 1000);

var nightTween2 = new TWEEN.Tween(DirectionalLight1)
.to({intensity: 1.3}, 1000);

var nightTween3 = new TWEEN.Tween(DirectionalLight2)
.to({intensity: 1.3}, 1000)

var nightTween1 = new TWEEN.Tween()
.chain(nightTween2,nightTween3,nightTween8,nightTween9)
.start();
}

//Animazione per esplosione pentola
kitchenAnimation=function(){
var kitchenTween2 = new TWEEN.Tween(pentola.position)
.to({z:0}, 2000)
.easing(TWEEN.Easing.Bounce.Out);


var kitchenTween1 = new TWEEN.Tween(pentola.position)
.to({z:10}, 100)
.delay(3000)
.chain(kitchenTween2)
.start();
}



  
