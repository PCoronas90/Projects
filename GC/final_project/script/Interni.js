//Funzione per la creazione della shape del camino
function asGeomCamino() {
var options = {
  amount: 1,
  bevelThickness: 0,
  bevelSize: 0.2,
  bevelSegments: 10,
  bevelEnabled: true,
  curveSegments: 3,};

var options2 = {
amount: 0.05,
bevelThickness: 0,
bevelSize: 0,
bevelSegments: 10,
bevelEnabled: true,
curveSegments: 3,};

//Camino
shape =createMeshCamino(drawShape().extrude(options),'textInt2.jpg');
//Piastrelle camino sui due lati
shape2 = createMeshCamino2(drawShape2().extrude(options2),'camino.jpg'); 
shape3 = createMeshCamino2(drawShape2().extrude(options2),'camino.jpg');

shape.rotation.x=Math.PI/2
shape.rotation.y=Math.PI/2
shape.add(shape2);
shape.add(shape3);

shape2.position.z=1.01;          
shape3.rotation.y=Math.PI
shape3.position.x=2;
shape3.position.z=-0.01;
     return shape;
    };

//Funzione per la creazione del camino e della funzione interact
function creaCamino(){
  camino=asGeomCamino();
  camino.interact=function(){
    startFire2()}
  return camino;}

function asGeomQuadroCamino() {
quadro = createMeshQuadro(new THREE.BoxGeometry(1.5, 0.05, 0.8), "quadro.jpg","quadro_bump.jpg");
quadro.rotation.z=Math.PI/2
return quadro;}   
      
function drawShape() {
var shape = new THREE.Shape();
shape.moveTo(0,0);

    shape.lineTo(2,0);
    shape.lineTo(2,2.4);
    shape.lineTo(0,2.4);

var hole1 = new THREE.Path([new THREE.Vector2(0.3,0.5),
new THREE.Vector2(1.7,0.5),
new THREE.Vector2(1.7,1.5),
new THREE.Vector2(0.3,1.5)]);
shape.holes.push(hole1);
return shape;}


function drawShape2() {
var shape = new THREE.Shape();
shape.moveTo(0.3,0.5);

    shape.lineTo(1.7,0.5);
    shape.lineTo(1.7,1.5);
    shape.lineTo(0.3,1.5);
    
var hole1 = new THREE.Path([new THREE.Vector2(0.5,0.7),
new THREE.Vector2(1.5,0.7),
new THREE.Vector2(1.5,1.3),
new THREE.Vector2(0.5,1.3)]);
shape.holes.push(hole1);
return shape;}


function createMeshCamino(geom,imageFile) {
var texture = THREE.ImageUtils.loadTexture("assets/textures/general/" + imageFile)
texture.wrapS = THREE.RepeatWrapping;
texture.wrapT = THREE.RepeatWrapping;
geom.computeVertexNormals();

var mat = new THREE.MeshLambertMaterial();
mat.map = texture;

var mesh = new THREE.Mesh(geom, mat);
mesh.material.map.wrapS = THREE.RepeatWrapping;
mesh.material.map.wrapT = THREE.RepeatWrapping;
mesh.material.map.repeat.set(1,1,1);
return mesh;}
      

 function createMeshCamino2(geom,imageFile) {
var texture = THREE.ImageUtils.loadTexture("assets/textures/general/" + imageFile)
texture.wrapS = THREE.RepeatWrapping;
texture.wrapT = THREE.RepeatWrapping;
geom.computeVertexNormals();

var mat = new THREE.MeshBasicMaterial();
mat.map = texture;

var mesh = new THREE.Mesh(geom, mat);
mesh.material.map.wrapS = THREE.RepeatWrapping;
mesh.material.map.wrapT = THREE.RepeatWrapping;
mesh.material.map.repeat.set(10,10,10);

return mesh;} 

function createMeshQuadro(geom, imageFile, bump) {
var texture = THREE.ImageUtils.loadTexture("assets/textures/general/" + imageFile);
var material = new THREE.MeshPhongMaterial({ map: texture,})
var normal = THREE.ImageUtils.loadTexture("assets/textures/general/" + bump);
material.normalMap = normal;
texture.wrapS = texture.wrapT = THREE.RepeatWrapping;
normal.wrapS = normal.wrapT = THREE.RepeatWrapping;
var mesh = new THREE.Mesh(geom,material);
return mesh;  
}




