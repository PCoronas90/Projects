//Statistiche
function initStats() {
  var stats = new Stats();
  stats.setMode(0); // 0: fps, 1: ms
  $('body').append(stats.domElement);
  return stats;      }

//Click's Animations
function onDocumentMouseDown(event) {
  event.preventDefault();
  if (document.pointerLockElement === element || document.mozPointerLockElement === element || document.webkitPointerLockElement === element) {
    var vector = new THREE.Vector3(0, 0, 0.5);
    projector.unprojectVector(vector, camera);
    var raycaster = new THREE.Raycaster(vector,
    controls.getDirection(new THREE.Vector3(0, 0, 0)).clone());
  } else {
  var vector = new THREE.Vector3(( event.clientX / window.innerWidth ) * 2 - 1, -( event.clientY / window.innerHeight ) * 2 + 1, 0.5);
  projector.unprojectVector(vector, camera);
  var raycaster = new THREE.Raycaster(camera.position, vector.sub(camera.position).normalize());}
  var intersects= raycaster.intersectObjects(casa.porte); 
  var intersects2= raycaster.intersectObjects([Video.Video,bottoneStereo,pomelloDoccia.levaDoccia,bottoneLight.bottoneLight,camino,fireBotton]);         
  if (intersects.length > 0) {           
      intersects[ 0 ].object.interact();  }
  if (intersects2.length > 0) {           
      intersects2[ 0 ].object.interact();}}

//Controlli sulle varie animazioni
function Animations(){
if(startAnimation===true){
  startFirstPerson;
  animate();}    

if (Video.video.readyState === Video.video.HAVE_ENOUGH_DATA) {
      if (Video.texture) Video.texture.needsUpdate = true;}

if(rain==true){
fire=false
engineRain.update( 0.01 * 0.5 );
//genera nuove particelle, se necessario; aggiorna i dati  di eventuali particelle
// attualmente esistenti; rimuove le particelle la cui età è maggiore dell'età massima  e 
//riutilizza i valori se nuove particelle devono essere create.
engineClouds.update( 0.01 * 0.5 );} 
if(rain==false){
engineRain.destroy();
engineClouds.destroy();
}
if(fire==true){
rain=false
engineFire.update( 0.01 * 0.5 );}
if(fire==false){
engineFire.destroy();}

if(fire2==true){
rain=false
engineFire2.update( 0.01 * 0.5 );}
if(fire2==false){
engineFire2.destroy();}

if(FireKitchen==true){
rain=false
engineFire3.update( 0.01 * 0.5 );}
if(FireKitchen==false){
engineFire3.destroy();}

if(shower==true){
engineShower.update( 0.01 * 0.5);}

if(night==true){
  engineStar.update( 0.01 * 0.5);}

if(snow==true){
  engineSnow.update( 0.01 * 0.5); }}


//Render
function render() {
stats.update();
trackballControls.update(); 
TWEEN.update(); 
Animations();
requestAnimationFrame(render);
webGLRenderer.render(scene, camera);

}

