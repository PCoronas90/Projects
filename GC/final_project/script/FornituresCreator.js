//Funzione per caricare oggetti Obj/Mtl
function createObject(obj,mtl,scalex,scaley,scalez,posx,posy,posz,rotationY){
  var Obj=new THREE.Object3D();
  var loader = new THREE.OBJMTLLoader();
  
    loader.load("assets/fornituresModels/"+obj,"assets/fornituresModels/"+mtl,function ( obj ) { 
     obj.rotation.x = Math.PI/2;
     if(rotationY!=null){
       obj.rotation.y = rotationY;}
     obj.scale.x = scalex;
      obj.scale.y =scaley;
     obj.scale.z = scalez;
      obj.position.set(posx,posy,posz);
      Obj.add( obj ); 
    } );   
    
    
    return Obj;
}

//Funzione per caricare oggetti Obj
function createObject2(obj,scalex,scaley,scalez,posx,posy,posz,rotationY){
  var Obj=new THREE.Object3D();
  var loader = new THREE.OBJLoader();
  
    loader.load("assets/fornituresModels/"+obj,function ( obj ) { 
     obj.rotation.x = Math.PI/2;
     if(rotationY!=null){
       obj.rotation.y = rotationY;}
     obj.scale.x = scalex;
      obj.scale.y =scaley;
     obj.scale.z = scalez;
      obj.position.set(posx,posy,posz);
      Obj.add( obj ); 
    } );   
    
    
    return Obj;
}

function createStartRadioBottom(){
var geometry = new THREE.CylinderGeometry( 0.02, 0.02, 0.02, 32,false );
var material = new THREE.MeshBasicMaterial( {color: 0xFF0000} );
bottoneStereo = new THREE.Mesh( geometry, material );

bottoneStereo.interact=function(){
  if(RadioSound==false){
    RadioSound=true;
    music.play();}
  else if(RadioSound==true){
    RadioSound=false;
    music.pause();
  }
}

return bottoneStereo;
}

function createStartLightBottom(){
interrutore=new THREE.Object3D();
interrutore.position.set(0,0,0)
var geometry = new THREE.CylinderGeometry( 0.02, 0.02, 0.01, 32,false );
var material = new THREE.MeshBasicMaterial( {color: 0x000000} );
bottone = new THREE.Mesh( geometry, material );

var geometry = new THREE.BoxGeometry( 0.01, 0.02, 0.01);
var material = new THREE.MeshBasicMaterial( {color: 0xFFFFFF});
bottoneLight = new THREE.Mesh( geometry, material );
bottoneLight.position.set(0,0.005,0)

interrutore.add(bottone);
interrutore.add(bottoneLight);
interrutore.bottoneLight=bottoneLight;

bottoneLight.interact=function(){
Camera01lightAnimation();}
return interrutore;

}

function createStartFireBottom(){
var geometry = new THREE.CylinderGeometry( 0.02, 0.02, 0.02, 32,false );
var material = new THREE.MeshBasicMaterial( {color: 0xFF0000} );
bottoneFire = new THREE.Mesh( geometry, material );

bottoneFire.interact=function(){
 startFireKitchen();
 if(FireKitchen==true){
 esplosione.play();}}

return bottoneFire;
}



function createStartShowerBottom(){

pomelloDoccia=new THREE.Object3D();

var geometry = new THREE.CylinderGeometry( 0.02, 0.02, 0.02, 32,false );
var material = new THREE.MeshPhongMaterial( {color: 0xCCCCFF} );
bottoneDoccia = new THREE.Mesh( geometry, material );
bottoneDoccia.position.set(0,-0.02,0);

var geometry1 = new THREE.CylinderGeometry( 0.06, 0.06, 0.01, 32,false );
var material1 = new THREE.MeshPhongMaterial( {color: 0xCCCCFF} );
bottoneDoccia2 = new THREE.Mesh( geometry1, material1 );

var geometry2 = new THREE.BoxGeometry( 0.02, 0.02, 0.07);
var material2 = new THREE.MeshPhongMaterial( {color: 0xCCCCFF} );
levaDoccia = new THREE.Mesh( geometry2, material2 );
levaDoccia.position.set(0,-0.03,-0.02)

pomelloDoccia.add(bottoneDoccia2);
pomelloDoccia.add(bottoneDoccia);
pomelloDoccia.add(levaDoccia);

pomelloDoccia.levaDoccia=levaDoccia;

levaDoccia.interact=function(){
  startShower();
}

return pomelloDoccia;
}

var arredoCucina= new THREE.Object3D();

  //lavaboCucina=createObject('kitchenSink.obj','kitchenSink.mtl',0.01,0.01,0.01,3.64,2.2,0.1,-Math.PI/2);
  //kitchenIsland1=createObject('kitchenIsland.obj','kitchenIsland.mtl',0.01,0.01,0.01,5.05,2.05,0.1,-Math.PI/2);
  //kitchenIsland2=createObject('kitchenIsland.obj','kitchenIsland.mtl',0.01,0.01,0.01,5.05,3.92,0.1,-Math.PI/2);
  //kitchenIsland3=createObject('kitchenIsland.obj','kitchenIsland.mtl',0.008,0.01,0.01,5.05,4.45,0.1,-Math.PI/2);
  //lavastoviglie=createObject('washing_machine.obj','washing_machine.mtl',0.01,0.01,0.01,3.8,0.5,-1,-Math.PI/2);
  macchinaGas=createObject('largeStove.obj','largeStove.mtl',0.01,0.009,0.01,3.25,3.7,0.1);
  hood=createObject('hood.obj','hood.mtl',0.01,0.009,0.01,4.4,3.35,0.5);
  //frigorifero=createObject('frigorifero_Scene.obj','frigorifero_Scene.mtl',0.3,0.3,0.3,3,2.5,2.1,Math.PI/2);
  //ripiano=createObject('kitchenUpperCabinet.obj','kitchenUpperCabinet.mtl',0.01,0.01,0.02,0.4,1.8,-1.15,Math.PI/2);
  //ripiano2=createObject('kitchenUpperCabinet.obj','kitchenUpperCabinet.mtl',0.01,0.01,0.02,0.4,1.8,-0.45,Math.PI/2);
  //ripiano3=createObject('kitchenUpperCabinet.obj','kitchenUpperCabinet.mtl',0.01,0.01,0.02,0.4,1.8,0.25,Math.PI/2);
  //tavoloCucina=createObject('white_kitchen_table.obj','white_kitchen_table.mtl',0.01,0.01,0.01,2,1.8,0.1,Math.PI/2);
  fireBotton=createStartFireBottom();
  fireBotton.position.set(2.68,3.45,0.76)
  pentola=createObject('bowl.obj','bowl.mtl',0.3,0.3,0.3,2.54,3.65,0.9);

  //arredoCucina.add(lavaboCucina)
  //arredoCucina.add(kitchenIsland1)
  //arredoCucina.add(kitchenIsland2)
  //arredoCucina.add(kitchenIsland3)
  //arredoCucina.add(lavastoviglie)
  arredoCucina.add(macchinaGas)
  arredoCucina.add(hood)
  //arredoCucina.add(frigorifero)
  //arredoCucina.add(ripiano)
  //arredoCucina.add(ripiano2)
  //arredoCucina.add(ripiano3)
  //arredoCucina.add(tavoloCucina)
  arredoCucina.add(fireBotton)
  arredoCucina.add(pentola)


var arredoCamera1=new THREE.Object3D();

  

var arredoCamera2=new THREE.Object3D();

  //lettoCamera2= createObject('juniorBed.obj','juniorBed.mtl',0.01,0.01,0.01,1.2,0.5,0.1,Math.PI/2)
  //comodinoCamera2= createObject('bedsideTable.obj','bedsideTable.mtl',0.01,0.01,0.01,0.2,1.5,0.2,Math.PI/2)
  scrivaniaCamera2= createObject('officeTable.obj','officeTable.mtl',0.015,0.01,0.01,1.55,2.7,0.1)
  //armadioCamera2=createObject('wardrobe.obj','wardrobe.mtl',0.013,0.01,0.01,4.2,1.75,0.1,-Math.PI/2)
  //libreriaCamera2=createObject('full-bookcase.obj', 'full-bookcase.mtl',0.01,0.01,0.01,3.8,3,1)
  stereo=createObject('stereo_amplifier.obj', 'stereo_amplifier.mtl',0.01,0.01,0.01,1.55,2.7,1)
  bottoneStereo=createStartRadioBottom();
  bottoneStereo.position.set(1.38,2.51,1)
  bottoneLight=createStartLightBottom()
  bottoneLight.position.set(2.6,0.115,1.5)
    
  //arredoCamera2.add(lettoCamera2)
  //arredoCamera2.add(comodinoCamera2)
  arredoCamera2.add(scrivaniaCamera2)
  //arredoCamera2.add(armadioCamera2)
  //arredoCamera2.add( libreriaCamera2)
  arredoCamera2.add(stereo)
  arredoCamera2.add(bottoneStereo)
  arredoCamera2.add(bottoneLight)

var  arredoBagno1= new THREE.Object3D();

  //water=createObject('water.obj','water.mtl',0.01,0.01,0.01,0.5,0.3,0.2,Math.PI);
  //bidet=createObject('bidet.obj','bidet.mtl',0.01,0.01,0.01,0.8,0.3,0.2,Math.PI);
  doccia=createObject('doccia.obj','doccia.mtl',0.01,0.01,0.01,1.05,-2.19,0.3,Math.PI/2);
  //lavaboBagno=createObject('bathroom_vanity.obj','bathroom_vanity.mtl',0.009,0.012,0.009,1.7,1.6,1);
  gettoDoccia=createObject('shower.obj','shower.mtl',0.01,0.01,0.01,0.7,1.4,1.5);

  pomelloDoccia=createStartShowerBottom();
  pomelloDoccia.position.set(0.7,1.78,1.35);
  
 // arredoBagno1.add(water)
  //arredoBagno1.add(bidet)
  arredoBagno1.add(doccia)
  //arredoBagno1.add(lavaboBagno)
  arredoBagno1.add(pomelloDoccia)
  arredoBagno1.add(gettoDoccia)

var arredosoggiorno1= new THREE.Object3D();

 // scrivania=createObject('bureau3.obj','bureau3.mtl',0.02,0.02,0.02,1.8,1,0.7,Math.PI);
 // sediaSoggiorno1=createObject('office-chair.obj','office-chair.mtl',0.1,0.1,0.1,2.1,1.8,0.5);
 // PcSoggiorno1=createObject('apple-ibook-2001.obj','apple-ibook-2001.mtl',1,1,1,2.1,1,1.4,Math.PI);
 // StampanteSoggiorno1=createObject('inkjet_and_scanner_printer.obj','inkjet_and_scanner_printer.mtl',0.015,0.015,0.015,1,1,1.5,Math.PI);
  
 // arredosoggiorno1.add(scrivania)
 // arredosoggiorno1.add(sediaSoggiorno1)
 // arredosoggiorno1.add(PcSoggiorno1)
 // arredosoggiorno1.add(StampanteSoggiorno1)

var arredoSoggiorno2= new THREE.Object3D();

  
  
  legna=createObject('fireplace.obj','fireplace.mtl',0.05,0.05,0.05,0.6,3,0.8)
  tv=createObject('tv.obj','tv.mtl',0.4,0.4,0.4,2,1.5,0.8, Math.PI/1.5)
  mobile=createObject('metal_glass_table.obj','metal_glass_table.mtl',0.01,0.01,0.01,2,1.5,0,-Math.PI/3)
  //sofa1=createObject('largeCouch.obj','largeCouch.mtl',0.4,0.4,0.4,3.8,11.5,0.3)
  //sofa2=createObject('couchPoofyPillows.obj','couchPoofyPillows.mtl',0.4,0.4,0.4,3.8,-1.8,0.3, Math.PI/2)
  

  arredoSoggiorno2.add(legna)
  arredoSoggiorno2.add(tv)
  arredoSoggiorno2.add(mobile)
  //arredoSoggiorno2.add(sofa1)
  //arredoSoggiorno2.add(sofa2)
  


var  arredoSalaPranzo= new THREE.Object3D();




