//Definizione della Gui
controlGUI = new function() {
this.First_Person = startFirstPerson ;
this.Rain_Animation=startRain;
this.Fire_Animation=startFire;
this.Night_Animation=startNight;
this.Snow_Animation=startSnow;
this.Trackball_Controls=true;} 

gui = new dat.GUI();
var Animations = gui.addFolder("Animations");
Animations.add(controlGUI, "First_Person");
Animations.add(controlGUI, "Rain_Animation"); 
Animations.add(controlGUI, "Fire_Animation"); 
Animations.add(controlGUI, "Night_Animation");
Animations.add(controlGUI, "Snow_Animation"); 

var TrackballControlsFolder = gui.addFolder("TrackballControls ");
TrackballControlsFolder.add(controlGUI, "Trackball_Controls").onChange(function (e){
trackballControls.enabled = e;});     
