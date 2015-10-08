//Creo un Object3d casa
casa = new THREE.Object3D();

//Funzione principale di caricamento degli obj delle varie stanze
 function caricamento(object,path,x,y,texturePath,TextDimX,TextDimY,TextPosZ,ArrayPorte,shape,arredo1,arredo2){
  object.load(path, function (obj) {
  Struttura = new THREE.Object3D();

   global_o = obj;
   var material = new THREE.MeshBasicMaterial({color: 0xFFFFCC});
   material.side = THREE.DoubleSide;
   obj.children[0].material = material;
   mesh = obj.children[0];         
   Struttura .add(mesh);
   texture=applicaTexture(texturePath,TextDimX,TextDimY,TextPosZ);
   mesh.add(texture);

   for(i=0;i<ArrayPorte.length;i++){
   mesh.add(ArrayPorte[i]);}  

   mesh.add(shape)
   mesh.add(arredo1)
   mesh.add(arredo2)

   var BulbGeometry = new THREE.CylinderGeometry( 0.1, 0.4, 0.1, 20, true );
   var BulbMaterial = new THREE.MeshPhongMaterial({color: '#CEF6F5',opacity: 1, transparent: false,shininess: 300,specular: '#ffffff', metal: false});
   Bulb = new THREE.Mesh( BulbGeometry, BulbMaterial);
   Bulb.CastShadow=true;
   Bulb.ReceiveShadow=true;
   Bulb.rotation.x=Math.PI/2
   
   var lampGeometry = new THREE.SphereGeometry(0.1,10,10);
   var lampMaterial = new THREE.MeshPhongMaterial({ color: '#ffffff', opacity: 0.5,shininess: 200,transparent: true,});
   Lamp = new THREE.Mesh( lampGeometry, lampMaterial);
   Lamp.add(Bulb)
   

  var light = new THREE.PointLight( 0xFFFFFF, 0, 50);
  Lamp.add(light)

  var Spotlight = new THREE.SpotLight( 0xFFFFFF, 0, 50);

  Spotlight.position.set(TextDimX/2, TextDimY/2, 2.4)
  Spotlight.target=Lamp;


  Lamp.position.set(TextDimX/2, TextDimY/2, 3 );
  Struttura.add(Lamp);
  Struttura.add(Spotlight);
  
  
   Struttura .position.set(2*x,2*y,0);
   Struttura.scale.x=2;
   Struttura.scale.y=2;
   Struttura.scale.z=2;

   HomeSpotLight.push(Spotlight);
   HomePointLight.push(light);
   casa.add( Struttura );});}   

//Funzione di caricamento globale 
function caricamentoCasa(){ 
var porte= new Array();   
 
var camera01Temp = new THREE.OBJLoader();
   port=creaPortaStandard(0.9,2.3,3.35,3.15,0);  
   infissi=creaInfissi(0.9,2.3,3.35,3.15,0); 

   finestra=creaFinestra(1,1.1,1.6,0.05,1.15);
   infissiFinestra=creaInfissiFinestra(1,1.2,2.1,0.05,1.1);

   var porteFinestreCamera01=[port,infissi,finestra,infissiFinestra]

   camera01=caricamento(camera01Temp,'assets/models/camera01.obj',0,0,'text3.jpg',4.2,3.2,0.2,porteFinestreCamera01,shapeCamera01,arredoCamera1);  
   porte.push(port.porta);

var camera02Temp = new THREE.OBJLoader();

   port2=creaPortaStandard(0.9,2.3,3.35,0.05,1); 
   infissi=creaInfissi(0.9,2.3,3.35,0.05,0);

   finestra=creaFinestra(1,1.1,1.6,3.15,1.15);
   infissiFinestra=creaInfissiFinestra(1,1.2,2.1,3.15,1.1)
         
   var porteFinestreCamera02=[port2,infissi,finestra,infissiFinestra]
   
   camera02=caricamento(camera02Temp,'assets/models/camera02.obj',0,4.9,'text3.jpg',4.2,3.2,0.2,porteFinestreCamera02,shapeCamera02,arredoCamera2);
   porte.push(port2.porta);

var bagno01Temp = new THREE.OBJLoader();

   portaBagno=creaPortaScorrevole(0.9,2.3,1.9,1.4,0);
   infissi=creaInfissi(0.9,2.3,1.9,1.4,0);
   portaBagno.rotation.z=Math.PI/2;
   infissi.rotation.z=Math.PI/2;

   finestra=creaFinestra(0.9,1.1,0.05,0.5,1.15);
   infissiFinestra=creaInfissiFinestra(0.9,1.2,-0.40,1.4,1.1); 
   finestra.rotation.y=Math.PI/2;
   infissiFinestra.rotation.z=Math.PI/2; 

   var porteFinestreBagno01=[portaBagno,infissi,finestra,infissiFinestra]     

   bagno01=caricamento(bagno01Temp,'assets/models/bagno01.obj',0,3.1,'text2.jpg',2.4,1.9,0.2,porteFinestreBagno01,shapeBagno01,arredoBagno1);
   porte.push(portaBagno.porta)

var stireria01Temp = new THREE.OBJLoader();
   portaStireria=creaPortaScorrevole(0.8,2.3,1.1,0.05,1); 
   infissi=creaInfissi(0.8,2.3,1.1,0.05,0); 
   portaStireria.rotation.z=Math.PI   
   infissi.rotation.z=Math.PI  

   finestra=creaFinestra(1,1.1,1.50,2.25,1.15);
   infissiFinestra=creaInfissiFinestra(0.95,1.2,2.00,2.25,1.1);

   var porteFinestreStireria=[portaStireria,infissi,finestra,infissiFinestra]

   stireria01=caricamento(stireria01Temp,'assets/models/stireria01.obj',10.8,8.2,'text11.jpg',3.1,2.3,.105,porteFinestreStireria,shapeStireria);
   porte.push(portaStireria.porta)

var cucinaTemp = new THREE.OBJLoader();
   portaCucina1=creaPortaScorrevole(0.9,2.3,0.75,0.05,1);
   portaCucina1.rotation.z=Math.PI
   infissi=creaInfissi(0.9,2.3,1.65,0.05,0);
   portaCucina2=creaPortaStandard(0.9,2.3,1.65,4.15,0);
   infissi2=creaInfissi(0.9,2.3,1.65,4.15,0);
   portaCucina3=creaPortaStandard(1.1,2.3,-0.5,3.80,2);
   portaCucina3.rotation.z=Math.PI/2
   infissi3=creaInfissi(1.1,2.3,-0.5,3.80,0);
   infissi3.rotation.z=Math.PI/2

   finestra=creaFinestra(1,1.1,4.15,1.598,1.15);
   infissiFinestra=creaInfissiFinestra(0.98,1.2,3.651,2.6,1.1);
   finestra.rotation.y=Math.PI/2;
   infissiFinestra.rotation.z=Math.PI/2;

   var porteFinestreCucina=[portaCucina1,portaCucina2,portaCucina3,infissi,infissi2,infissi3,finestra,infissiFinestra]

   cucina=caricamento(cucinaTemp,'assets/models/cucina.obj',13.2,4.1,'textCucina.jpg',4.2,4.2,0.11,porteFinestreCucina,shapeCucina,arredoCucina);
   porte.push(portaCucina1.porta)
   porte.push(portaCucina2.porta)
   porte.push(portaCucina3.porta)

var bagno2Temp = new THREE.OBJLoader();
   portaBagno2=creaPortaScorrevole(1,2.3,1,1.35,1);  
   infissi=creaInfissi(1,2.3,1,1.35,0);
   portaBagno2.rotation.z=Math.PI
   infissi.rotation.z=Math.PI

   var porteFinestreBagno02=[portaBagno2,infissi]

   bagno2=caricamento(bagno2Temp,'assets/models/bagno2.obj',10.2,5.3,'textBagno2.jpg',3,1.4,0.25,porteFinestreBagno02,shapeBagno02);
   porte.push(portaBagno2.porta)


var scaleTemp = new THREE.OBJLoader();
   port=creaPortaStandard(1.0,2.3,3.05,1.3);
   infissi=creaInfissi(1.0,2.3,3.05,1.3,0);
   port.rotation.z=Math.PI/2
   infissi.rotation.z=Math.PI/2
   
   finestra=creaFinestra(1,1.1,1.35,1.55,1.15);
   infissiFinestra=creaInfissiFinestra(1,1.2,1.85,1.55,1.1);

   var porteFinestreScale=[port,infissi,finestra,infissiFinestra]

   scale=caricamento(scaleTemp,'assets/models/scale.obj',4.1,6.6,'text11.jpg',3.6,1.6,0.2,porteFinestreScale,shapeScale);

var soggiorno01Temp = new THREE.OBJLoader();
   portaSoggiorno1=creaFinestroni(1.6,2.1,0.95,0.05,3);
   infissi=creaInfissi(1.6,2.2,1.75,0.05,0);
   infissi.position.z=1.2;  
 
   var porteFinestreSoggiorno01=[portaSoggiorno1,infissi]  

   soggiorno01=caricamento(soggiorno01Temp,'assets/models/Soggiorno01.obj',4.1,0.5,'text11.jpg',3.5,6.2,0.105, porteFinestreSoggiorno01,shapeSoggiorno01, arredosoggiorno1);
   porte.push( portaSoggiorno1.specchio2)

var soggiorno02Temp = new THREE.OBJLoader();
   finestroneSoggiorno2=creaFinestroni(1.9,2.1,0.5,0.05,0);
   infissi=creaInfissi(1.9,2.2,1.45,0.05,0.1);

   finestrone2Soggiorno2=creaFinestroni(1.9,2.1,3.4,0.05,1);
   infissi2=creaInfissi(1.9,2.2,4.35,0.05,0.1);

   var arredoSoggiorno22=new THREE.Object3D();

   camino=creaCamino();
   camino.position.set(0,2,0.1);

   quadro=asGeomQuadroCamino();
   quadro.position.set(1,3,2.2);

   Video.position.set(2.031,1.5038,1.4);
   arredoSoggiorno22.add(camino)
   arredoSoggiorno22.add(quadro)
   arredoSoggiorno22.add(Video)
      
   var porteFinestreSoggiorno02=[finestroneSoggiorno2,finestrone2Soggiorno2,infissi,infissi2]     

   soggiorno02=caricamento(soggiorno02Temp,'assets/models/Soggiorno02.obj',7.6,0,'text11.jpg',5.6,5.4,.105,porteFinestreSoggiorno02,shapeSoggiorno02,arredoSoggiorno2,arredoSoggiorno22);
   porte.push(finestroneSoggiorno2.specchio2);
   porte.push(finestrone2Soggiorno2.specchio2);
 
var SalaPranzoTemp = new THREE.OBJLoader();
   finestroneSalaPranzo=creaFinestroni(1.9,2.1,1.2,0.05,2);
   infissi=creaInfissi(1.9,2.2,2.15,0.05,0.1);

   var porteFinestreSalaPranzo=[finestroneSalaPranzo,infissi,shapeSalaPranzo] 
   SalaPranzo=caricamento(SalaPranzoTemp,'assets/models/SalaPranzo.obj',13.2,0.5,'text11.jpg',4.2,3.7,.105, porteFinestreSalaPranzo,shapeSalaPranzo,arredoSalaPranzo);
   porte.push( finestroneSalaPranzo.specchio2)
      
var remove02Temp = new THREE.OBJLoader();
   finestra=creaFinestra(1.1,1.1,1.3,5.15,1.15);
   infissiFinestra=creaInfissiFinestra(1.1,1.2,1.85,5.15,1.1);

   portRemove2=creaPortaStandard(1,2.3,-0.45,5,0)
   infissi2=creaInfissi(1,2.3,-0.45,5,0); 

   portRemove2.rotation.z=Math.PI/2;
   infissi2.rotation.z=Math.PI/2;

   var porteFinestreRemove02=[finestra,infissiFinestra,portRemove2,infissi2] 

  remove02=caricamento(remove02Temp,'assets/models/Remove02.obj',7.5,5.3,'text11.jpg',5.8,5.2,.115,porteFinestreRemove02,shapeRemove02);
  porte.push(portRemove2.porta);

var remove03Temp = new THREE.OBJLoader();
   portRemove=creaPortaScorrevole(1.2,2.3,1.15,1.5,0);
   infissi=creaInfissi(1.2,2.3,1.15,1.5,0); 
   portRemove.rotation.z=Math.PI/2;
   infissi.rotation.z=Math.PI/2;

   var porteFinestreRemove03=[portRemove,infissi] 

   remove03=caricamento(remove03Temp,'assets/models/Remove03.obj',2.4,3.1,'text11.jpg',1.8,1.8,.11,porteFinestreRemove03);
   porte.push(portRemove.porta);

casa.porte=porte;


return casa;}


