//Inizializzazione
function init(){

startAnimation=false;
Video=createVideo();
rayPointer = new THREE.Raycaster();
stats = initStats()
trackballControls = new THREE.TrackballControls(camera);
projector = new THREE.Projector();
document.addEventListener('mousedown', onDocumentMouseDown, false);

webGLRenderer = new THREE.WebGLRenderer();
webGLRenderer.setClearColor(new THREE.Color(0x0066FF));
webGLRenderer.setSize(window.innerWidth, window.innerHeight);
webGLRenderer.shadowMapEnabled = true;


camera.position.set(0, 20,100);
camera.lookAt(scene.position);
camera.up = new THREE.Vector3(0,2,0);
scene.add(camera);

DirectionalLight1 = new THREE.DirectionalLight(0xffffff,1.3);
DirectionalLight1.position.set(40, 30, -20);

DirectionalLight2 = new THREE.DirectionalLight(0xffffff,1.3);
DirectionalLight2.position.set(-40, 30,30);

//DirectionalLight3 = new THREE.DirectionalLight(0xffffff,0.6);
//DirectionalLight3.position.set(0, -10,0);



scene.add(DirectionalLight1);
scene.add(DirectionalLight2);
//scene.add(DirectionalLight3);

Giardino=GardenCreator();
casa=caricamentoCasa();
casa.add(Giardino)
casa.Giardino=Giardino;
casa.rotation.x=-Math.PI/2
casa.position.set(-25,0,0);
casa.scale.x=2
casa.scale.y=2
casa.scale.z=2
scene.add(casa)
objects.push(casa)

rain=false;
fire=false;
shower=false;

var lati = ["1", "2", "5", "0", "3", "4"];
var materials = [];

//Creo un cubo con solo facce interne per il paesaggio intorno alla casa
for (var i = 0; i < 6; i++)
  materials.push(new THREE.MeshLambertMaterial({
    map: THREE.ImageUtils.loadTexture("assets/textures/general/" + "paesaggio_" + lati[i] + ".jpg"),
    side: THREE.BackSide
  }));

var paesaggio_material = new THREE.MeshFaceMaterial(materials);
paesaggio = new THREE.Mesh(new THREE.BoxGeometry(250, 250,250), paesaggio_material);
paesaggio.color = new THREE.Color('#FFFFFF');
paesaggio.position.set(20,124,0);
//scene.add(paesaggio);


//Faccio lo stesso per il paesaggio innevato
var materials = [];

for (var i = 0; i < 6; i++){
  materials.push(new THREE.MeshLambertMaterial({
    map: THREE.ImageUtils.loadTexture("assets/textures/general/" + "neve_" + lati[i] + ".jpg"),
    side: THREE.BackSide
  }))};

var paesaggioInnevato_material = new THREE.MeshFaceMaterial(materials);
paesaggioInnevato = new THREE.Mesh(new THREE.BoxGeometry(250, 250,250), paesaggioInnevato_material);
paesaggioInnevato.color = new THREE.Color('#FFFFFF');
paesaggioInnevato.position.set(20,124,0); 

}