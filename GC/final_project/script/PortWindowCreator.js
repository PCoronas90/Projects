//Funzione di applicazione texture a porte e finestre
function createMeshPortWindow(geom,imageFile) {
  var texture = THREE.ImageUtils.loadTexture("assets/textures/general/" + imageFile)
  texture.wrapS = THREE.RepeatWrapping;
  texture.wrapT = THREE.RepeatWrapping;
  geom.computeVertexNormals();

  var mat = new THREE.MeshPhongMaterial();
  mat.map = texture;

  var mesh = new THREE.Mesh(geom, mat);
  mesh.material.map.wrapS = THREE.RepeatWrapping;
  mesh.material.map.wrapT = THREE.RepeatWrapping;
  mesh.material.map.repeat.set(1,1);
  return mesh;
}

//Funzione di applicazione texture al pavimento della stanza
function applicaTexture(TexturePath,x,y,z){
  var plane = createMeshPortWindow(new THREE.PlaneGeometry(x,y,20,20), TexturePath);
  plane.position.x=x/2;
  plane.position.y=y/2;
  plane.position.z = z;
  plane.material.map.wrapS = THREE.RepeatWrapping;
  plane.material.map.wrapT = THREE.RepeatWrapping;


  plane.material.map.repeat.set(x*2,y*2);
  plane.material.map.needsUpdate = true;
  return plane
}

//Funzione per la creazione di porte standard
function creaPortaStandard(PortdimX,PortdimY,PortPosX,PortPosY,apertura){
var port = new THREE.Object3D();
var porta = createMeshPortWindow(new THREE.BoxGeometry(PortdimX, 0.1, PortdimY), 'PortText.jpg');
porta.position.set(-PortdimX/2,0,0);

pomello1=createMeshPortWindow(new THREE.CylinderGeometry(0.025,0.025 ,0.09,false),'PomelloText.jpg');
pomello2=createMeshPortWindow(new THREE.CylinderGeometry(0.025,0.025 ,0.09,false),'PomelloText.jpg');
//pomello1.rotation.x=Math.PI/2;
pomello1.position.x=-0.25
pomello1.position.y=0.05;

pomello2.position.x=-(PortdimX/2)+0.2
pomello2.position.y=-0.05;

porta.add(pomello1);
porta.add(pomello2);
port.add(porta);
port.position.set(PortPosX+(PortdimX/2),PortPosY,PortdimY/2);
port.porta=porta;

if(apertura==0){
porta.interact=function(){
  if(port.rotation.z==0){
     portSound_open.play();
      portAnimation1(port);}
  else {
     portSound_close.play();
      portAnimation2(port);}}}

if(apertura==1){
porta.interact=function(){
  if(port.rotation.z==0){
    portSound_open.play();
    portAnimation3(port);}
  else {
    portSound_close.play();
    portAnimation4(port);}}}

  if(apertura==2){
porta.interact=function(){
  if(port.rotation.z==Math.PI/2){
     portSound_open.play();
          portAnimation5(port);}
  else {
     portSound_close.play();
        portAnimation6(port);}}}
 return port;}


//Funzione per la creazione di porte scorrevoli
function creaPortaScorrevole(PortdimX,PortdimY,PortPosX,PortPosY,apertura){
var port = new THREE.Object3D();
var porta = createMeshPortWindow(new THREE.BoxGeometry(PortdimX, 0.1, PortdimY), 'PortText.jpg');
porta.position.set(-PortdimX/2,0,0);

pomello1=createMeshPortWindow(new THREE.CylinderGeometry(0.05,0.05 ,0.025,false),'PomelloText.jpg');
pomello2=createMeshPortWindow(new THREE.CylinderGeometry(0.05,0.05 ,0.025,false),'PomelloText.jpg');
//pomello1.rotation.x=Math.PI/2;
pomello1.position.x=0.25
pomello1.position.y=0.05;

pomello2.position.x=(PortdimX/2)-0.2
pomello2.position.y=-0.05;

porta.add(pomello1);
porta.add(pomello2);
port.add(porta);
port.position.set(PortPosX+(PortdimX/2),PortPosY,PortdimY/2);
port.porta=porta;
port.PortdimX=PortdimX;
port.PortPosX=PortPosX;
port.PortPosY=PortPosY;
if(apertura==0){
porta.interact=function(){
if(port.position.y==PortPosY){
  portaScorrevoleSound.play();
  portAnimation7(port)}
else {
  portaScorrevoleSound.play();
  portAnimation8(port);}}}

 if(apertura==1){
porta.interact=function(){
  if(port.position.x==PortPosX+(PortdimX/2)){
    portaScorrevoleSound.play();
    portAnimation9(port)  }
  else {
    portaScorrevoleSound.play();
    portAnimation10(port)}}}
return port;

}

//Funzione di creazione finestroni
function creaFinestroni(DimX,DimY,PosX,PosY,aperturaFin){
var Finestrone1 = new THREE.Object3D();
var Finestrone2 = new THREE.Object3D();
var Perno = new THREE.Object3D();

var trave1 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave1.position.set(DimX/4,0,0)
var trave2 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave2.position.set(DimX-(DimX/4),0,0)
var trave3 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1),'PortText.jpg');
trave3.position.set(DimX-(DimX/4),DimY,0)
var trave4 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave4.position.set(DimX/4,DimY,0)

var trave5 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave5.position.set(0.05,DimY/2,0)
var trave6 =createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave6.position.set(DimX/2,DimY/2,0)

var trave8 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave8.position.set(DimX/2,DimY/2,0)

var trave7 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave7.position.set(DimX-0.05,DimY/2,0)

var specchio1Geometry = new THREE.BoxGeometry((DimX/2)-0.15, DimY-0.1, 0.1);
 var mGlass = new THREE.MeshLambertMaterial( {
  color:  0xccccff,opacity: 0.3, transparent: true} );   

specchio1 = new THREE.Mesh( specchio1Geometry, mGlass);
specchio1.position.set((DimX/4)+0.02,(DimY/2),0)

specchio2 = new THREE.Mesh( specchio1Geometry, mGlass);
specchio2.position.set((DimX-(DimX/4)-0.02),(DimY/2),0)

Finestrone1.add(trave1)
Finestrone1.add(trave4)
Finestrone1.add(trave5)
Finestrone1.add(trave6)
Finestrone1.add(specchio1)
Finestrone1.add(Perno)

Finestrone2.add(trave2)
Finestrone2.add(trave3)
Finestrone2.add(trave7)
Finestrone2.add(trave8)
Finestrone2.add(specchio2)
Perno.add(Finestrone2)

Finestrone1.position.set(PosX,PosY,0.15);
Finestrone1.rotation.x=Math.PI/2
Finestrone1.specchio2=specchio2

Finestrone2.PosX=PosX
Finestrone2.PosY=PosY
Finestrone2.DimX=DimX

  if(aperturaFin==0){
  specchio2.interact=function(){
    if(Finestrone2.position.x==PosX-((DimX/4)+0.025)){
      finestroneSound.play();
finestroneAnimation1(Finestrone2)} 
            else {
      finestroneSound.play();
finestroneAnimation2(Finestrone2)}}}

  if(aperturaFin==1){
  specchio2.interact=function(){
    if(Finestrone2.position.x==PosX-(((DimX/4)+0.025)+(DimX*2))){
        finestroneSound.play();
    finestroneAnimation4(Finestrone2)} 
    else {
       finestroneSound.play();
finestroneAnimation3(Finestrone2)}}}

  if(aperturaFin==2){
  specchio2.interact=function(){
    if(Finestrone2.position.x==PosX-((DimX/2)+0.25)){
       finestroneSound.play();
finestroneAnimation5(Finestrone2)} 
            else {
        finestroneSound.play();
finestroneAnimation6(Finestrone2)}}}

    if(aperturaFin==3){
  specchio2.interact=function(){
    if(Finestrone2.position.x==PosX-(DimX+0.15)){
      finestroneSound.play();
finestroneAnimation8(Finestrone2)} 
            else {
        finestroneSound.play();
finestroneAnimation7(Finestrone2)}}}
  return Finestrone1
  }


//Funzione per la creazione degli infissi
function creaInfissi(PortdimX,PortdimY,PortPosX,PortPosY,PortPosZ){
var infissi = new THREE.Object3D();

var infisso1 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso1.position.set(-(PortdimX+0.05),-0.06,0);
var infisso2 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso2.position.set(0.05,-0.06,0);

var infisso3 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso3.position.set(-(PortdimX+0.05),0.06,0);
var infisso4 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso4.position.set(0.05,0.06,0);

var infisso5 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1), 'PortText.jpg');
infisso5.position.set(-(PortdimX/2),0.06,(PortdimY/2)+0.05);
var infisso6 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1), 'PortText.jpg');
infisso6.position.set(-(PortdimX/2),-0.06,(PortdimY/2)+0.05);

infissi.add(infisso1)
infissi.add(infisso2)
infissi.add(infisso3)
infissi.add(infisso4)
infissi.add(infisso5)
infissi.add(infisso6)

infissi.position.set(PortPosX+(PortdimX/2),PortPosY,(PortdimY/2)+PortPosZ);
return infissi;}

//Funzione per la creazione delle finestre
function creaFinestra(DimX,DimY,PosX,PosY,PosZ){
var Finestrone1 = new THREE.Object3D();
var Finestrone2 = new THREE.Object3D();
var Perno = new THREE.Object3D();

var trave1 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave1.position.set(DimX/4,0,0)
var trave2 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave2.position.set(DimX-(DimX/4),0,0)
var trave3 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1),'PortText.jpg');
trave3.position.set(DimX-(DimX/4),DimY,0)
var trave4 = createMeshPortWindow(new THREE.BoxGeometry(DimX/2, 0.1, 0.1), 'PortText.jpg');
trave4.position.set(DimX/4,DimY,0)

var trave5 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave5.position.set(0.05,DimY/2,0)
var trave6 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave6.position.set(DimX/2,DimY/2,0)

var trave8 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave8.position.set(DimX/2,DimY/2,0)

var trave7 = createMeshPortWindow(new THREE.BoxGeometry(0.1, DimY-0.1, 0.1), 'PortText.jpg');
trave7.position.set(DimX-0.05,DimY/2,0)

var specchio1Geometry = new THREE.BoxGeometry((DimX/2)-0.15, DimY-0.1, 0.1);
 var mGlass = new THREE.MeshLambertMaterial( {
  color:  0xccccff,opacity: 0.3, transparent: true} );   

specchio1 = new THREE.Mesh( specchio1Geometry, mGlass);
specchio1.position.set((DimX/4)+0.03,(DimY/2),0)

specchio2 = new THREE.Mesh( specchio1Geometry, mGlass);
specchio2.position.set((DimX-(DimX/4)-0.03),(DimY/2),0)

Finestrone1.add(trave1)
Finestrone1.add(trave4)
Finestrone1.add(trave5)
Finestrone1.add(trave6)
Finestrone1.add(specchio1)
Finestrone1.add(Perno)

Finestrone2.add(trave2)
Finestrone2.add(trave3)
Finestrone2.add(trave7)
Finestrone2.add(trave8)
Finestrone2.add(specchio2)
Perno.add(Finestrone2)

Finestrone1.position.set(PosX,PosY,PosZ);
Finestrone1.rotation.x=Math.PI/2

return Finestrone1
  }

//Funzione per la creazione degli infissi delle varie finestre
function creaInfissiFinestra(PortdimX,PortdimY,PortPosX,PortPosY,PortPosZ){
var infissi = new THREE.Object3D();

var infisso1 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso1.position.set(-(PortdimX+0.05),-0.05,0);
var infisso2 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso2.position.set(0.05,-0.05,0);

var infisso3 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso3.position.set(-(PortdimX+0.05),0.05,0);
var infisso4 = createMeshPortWindow(new THREE.BoxGeometry(0.1, 0.02, PortdimY), 'PortText.jpg');
infisso4.position.set(0.05,0.05,0);

var infisso5 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1), 'PortText.jpg');
infisso5.position.set(-(PortdimX/2),0.05,(PortdimY/2)+0.05);
var infisso6 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1), 'PortText.jpg');
infisso6.position.set(-(PortdimX/2),-0.05,(PortdimY/2)+0.05);

var infisso7 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1),'PortText.jpg');
infisso7.position.set(-((PortdimX/2)),-0.05,-((PortdimY/2)+0.05));

var infisso8 = createMeshPortWindow(new THREE.BoxGeometry(PortdimX+0.2, 0.02, 0.1),'PortText.jpg');
infisso8.position.set(-((PortdimX/2)),0.05,-((PortdimY/2)+0.05));

infissi.add(infisso1)
infissi.add(infisso2)
infissi.add(infisso3)
infissi.add(infisso4)
infissi.add(infisso5)
infissi.add(infisso6)
infissi.add(infisso7)
infissi.add(infisso8)

infissi.position.set(PortPosX+(PortdimX/2),PortPosY,(PortdimY/2)+PortPosZ);
return infissi;


  }