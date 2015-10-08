//Animazione della pioggia
 var settingsRain = {
      positionStyle    : Type.CUBE,
      //position
      positionBase     : new THREE.Vector3( -40, 60, 00 ),
      //dimension
      positionSpread   : new THREE.Vector3( 200, 100, 100 ),
 
      velocityStyle    : Type.CUBE,
      velocityBase     : new THREE.Vector3( 0, -180, 0 ),
      velocitySpread   : new THREE.Vector3( 0, -120, 0 ), 
      accelerationBase : new THREE.Vector3( 0, -80, 0 ),
       
      particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/raindrop2flip.png' ),
 
      sizeBase    : 0.8,
      sizeSpread  : 0.3,
      colorBase   : new THREE.Vector3(0.66, 1.0, 0.7), // H,S,L
      colorSpread : new THREE.Vector3(0.00, 0.0, 0.2),
      opacityBase : 0.6,
 
      particlesPerSecond : 10000,
      particleDeathAge   : 0.2,  
      emitterDeathAge    : 60
     };

//Animazione della doccia
 var settingsShower = {
  //Quando le particelle vengono create, le loro proprietà sono impostati su valori casuali 
  //nell'intervallo (base - spread / 2, base + spread / 2); 
  //Forma dell'emettitore
      positionStyle    : Type.CUBE,
  //Dimensione e posizione dell'emettitore
      positionBase     : new THREE.Vector3( -22.2,7.4,-18.8 ),
      positionSpread   : new THREE.Vector3( 0.4, 7.2, 0.4 ),
  //Movimento   
      velocityStyle    : Type.CUBE,
  //Velocità di base
      velocityBase     : new THREE.Vector3( 0, -100, 0 ),
  //Velocità di diffusione
      velocitySpread   : new THREE.Vector3( 0, -5, 0 ), 
  //Eventuale accellerazione
      accelerationBase : new THREE.Vector3( 0, -80, 0 ),
       
      particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/raindrop2flip.png' ),
 
      sizeBase    : 0.3,
      sizeSpread  : 0.2,
      colorBase   : new THREE.Vector3(0.66, 1.0, 0.7), // H,S,L
      colorSpread : new THREE.Vector3(0.00, 0.0, 0.2),
      opacityBase : 0.6,
 
      particlesPerSecond : 2000,
      particleDeathAge   : 0.1,  
      emitterDeathAge    : 60
     };

//Animazione delle nuvole
var settingClouds =
      {
        //Forma dell'emitter
            positionStyle  : Type.CUBE,
        //Posizione dell'emettitore
            positionBase   : new THREE.Vector3(-40, 80, 00 ),
        //Dimensione
            positionSpread : new THREE.Vector3(120, 15, 100 ),
        //Movimento   
            velocityStyle  : Type.CUBE,
        //Velocità di base
            velocityBase   : new THREE.Vector3( 40, 0, 0 ),
        //Velocità di diffusione
            velocitySpread : new THREE.Vector3( 20, 0, 0 ), 

        //Immagine delle singole particelle    
            particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/smokeparticle.png'),
        //Dimensione particelle
            sizeBase     : 120.0,
        //Grandezza di diffusione
            sizeSpread   : 120.0,
        //Colore di base
            colorBase    : new THREE.Vector3(0.0, 0.0, 1.0), // H,S,L
        //Tween amination per modificare l'opacità
            opacityTween : new Tween([0,1,4,5],[0,1,1,0]),

         //Particelle al secondo
            particlesPerSecond : 180,
          //Tempo di vita delle particelle
            particleDeathAge   : 3,       
          //Tempo di vita dell'emitter.  L'emitter si occupa di gestire e aggiornare tutti i dati delle singole particelle.
            emitterDeathAge    : 35
      };
//Animazione del fuoco
var settingFire=      {
            positionStyle  : Type.SPHERE,
            positionBase   : new THREE.Vector3( 6.8, 4, -12 ),
            positionRadius : 1,
            
            velocityStyle  : Type.CUBE,
            velocityBase   : new THREE.Vector3(0,12,0),
            velocitySpread : new THREE.Vector3(2,0,2),
            
            particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/smokeparticle.png' ),
            
            //Modifica dimensione,colore e opacità delle particelle. Sono due array: tempo e dimensione. All'istante t (primo array)
            //la dimensione della particella è portata alla dimensione d(secondo array).
            sizeTween    : new Tween( [0, 0.03, 0.12], [1.5, 3, 1.5] ),
            opacityTween : new Tween( [1.6, 2.2], [2.1, 0.5] ),
            //Colori in formato hsl: Tonalità,saturazione,luminosità.In questo caso a due istanti di tempo diversi si avranno due
            //colori diversi.
            colorTween   : new Tween( [0.5, 1.0], [ new THREE.Vector3(0.02, 1, 0.5), new THREE.Vector3(0.05, 1, 0) ] ),
            //Stile di fusione delle particelle. Di base è normale.
            blendStyle : THREE.AdditiveBlending,  
            
            particlesPerSecond : 800,
            particleDeathAge   : 0.07,           
            emitterDeathAge    : 60
      };
//Animazione del fuoco in First Person
var settingFire2=      {
            positionStyle  : Type.SPHERE,
            positionBase   : new THREE.Vector3( 6.8, 6, -12 ),
            positionRadius : 1,
            
            velocityStyle  : Type.CUBE,
            velocityBase   : new THREE.Vector3(0,12,0),
            velocitySpread : new THREE.Vector3(2,0,2),
            
            particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/smokeparticle.png' ),
            
            sizeTween    : new Tween( [0, 0.03, 0.12], [1.5, 3, 1.5] ),
            opacityTween : new Tween( [1.6, 2.2], [2.1, 0.5] ),
            colorTween   : new Tween( [0.5, 1.0], [ new THREE.Vector3(0.02, 1, 0.5), new THREE.Vector3(0.05, 1, 0) ] ),
            blendStyle : THREE.AdditiveBlending,  
            
            particlesPerSecond : 800,
            particleDeathAge   : 0.07,           
            emitterDeathAge    : 60
      };

//Animazione del fuoco dei fornelli della cucina
var settingFire3=      {
            positionStyle  : Type.SPHERE,
            positionBase   : new THREE.Vector3( 37.93, 5.3, -31.1 ),
            positionRadius : 0.18,
            
            velocityStyle  : Type.CUBE,
            velocityBase   : new THREE.Vector3(0,1.2,0),
            velocitySpread : new THREE.Vector3(0.2,0,0.2),
            
            particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/smokeparticle.png' ),
            
            sizeTween    : new Tween( [0, 0.03, 0.12], [0.01,0.2, 0.01] ),
            opacityTween : new Tween( [1.6, 2.2], [2.1, 0.5] ),
            colorTween   : new Tween( [0.5, 1.0], [ new THREE.Vector3(0.66, 1.0, 0.7), new THREE.Vector3(0.0, 0.0, 0.2) ] ),
            blendStyle : THREE.AdditiveBlending,  
            
            particlesPerSecond : 800,
            particleDeathAge   : 0.07,           
            emitterDeathAge    : 60
      };


//Animazione delle stelle
var settingsStar =
  {
    positionStyle    : Type.CUBE,
    positionBase     : new THREE.Vector3( 0, 200, 0 ),
    positionSpread   : new THREE.Vector3( 250, 100, 250 ),

    velocityStyle    : Type.CUBE,
    velocityBase     : new THREE.Vector3( 0, 0, 0 ),
    velocitySpread   : new THREE.Vector3( 0.5, 0.5, 0.5 ), 
    
    angleBase               : 0,
    angleSpread             : 720,
    angleVelocityBase       : 0,
    angleVelocitySpread     : 4,

    particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/spikey.png' ),
    
    sizeBase    : 10.0,
    sizeSpread  : 2.0,        
    colorBase   : new THREE.Vector3(0.15, 1.0, 0.9), // H,S,L
    colorSpread : new THREE.Vector3(0.00, 0.0, 0.2),
    opacityBase : 1,

    particlesPerSecond : 2000,
    particleDeathAge   : 60.0,    
    emitterDeathAge    : 0.1
  };

//Animazione della neve
var snowSettings =
  {
    positionStyle    : Type.CUBE,
    positionBase     : new THREE.Vector3( 0, 200, 0  ),
    positionSpread   : new THREE.Vector3(250, 100, 250 ),
    
    velocityStyle    : Type.CUBE,
    velocityBase     : new THREE.Vector3( 0, -60, 0 ),
    velocitySpread   : new THREE.Vector3( 50, 20, 50 ), 
    accelerationBase : new THREE.Vector3( 0, -10,0 ),
    
    angleBase               : 0,
    angleSpread             : 720,
    angleVelocityBase       :  0,
    angleVelocitySpread     : 60,
    
    particleTexture : THREE.ImageUtils.loadTexture( 'assets/textures/general/snowflake.png' ),
      
    sizeTween    : new Tween( [0, 0.25], [1, 10] ),
    colorBase   : new THREE.Vector3(0.66, 1.0, 0.9), // H,S,L
    opacityTween : new Tween( [2, 3], [0.8, 0] ),

    particlesPerSecond : 200,
    particleDeathAge   : 4.0,   
    emitterDeathAge    : 60
  };

//Inizializzazione dei vari Engine delle animazioni
engineRain = new ParticleEngine();

//Creo il particle engine con i valori di default.
engineClouds = new ParticleEngine();
//Gli passo come valori i setting creati precedentemente. Verranno aggiornati tramite l'update nel render()
engineClouds.setValues( settingClouds );

engineFire = new ParticleEngine();
engineFire.setValues( settingFire );

engineFire2 = new ParticleEngine();
engineFire2.setValues( settingFire2 );

engineFire3 = new ParticleEngine();
engineFire3.setValues( settingFire3 );

engineShower= new ParticleEngine();
engineShower.setValues(settingsShower);

engineStar= new ParticleEngine();
engineStar.setValues(settingsStar); 

engineSnow= new ParticleEngine();
fp=false;

//Funzione di animazione della pioggia
function startRain(){
  if(rain==false){
    rain=true;
    if(fire==true){
    engineFire.destroy();
    startFire(); }
    
    engineRain.setValues( settingsRain );
    lightAnimation();
  rainAnimation();
  soundOfRain.volume=1;
  soundOfRain.play();
   }
else{
  rain=false
  lightAnimation();  
   engineRain.destroy();
   engineClouds.destroy();

  rainAnimation2();
  soundOfRain.pause();


  engineClouds = new ParticleEngine();
  engineClouds.setValues( settingClouds );

}
engineRain.initialize();
engineClouds.initialize();} 

//Funzione di animazione del fuoco
function startFire(){
if(fire==true){
  engineFire.destroy();
fire=false;
fireSound.pause();}
else{
fire=true;
if(rain==true){
engineRain.destroy();
engineClouds.destroy();
startRain();}
if(snow==true){
engineSnow.destroy();
startSnow();}

fireSound.play();
engineFire = new ParticleEngine();
engineFire.setValues( settingFire );
engineFire.initialize();
}
engineFire.initialize();
}

//Funzione di animazione del fuoco della cucina
function startFireKitchen(){
if(fp==true){
if(FireKitchen==true){
engineFire3.destroy();
FireKitchen=false;
fireKitchenSound.pause();}
else{
kitchenAnimation();
FireKitchen=true;
if(rain==true){
engineRain.destroy();
engineClouds.destroy();
startRain();}
if(snow==true){
engineSnow.destroy();
startSnow();}

fireKitchenSound.play();
engineFire3 = new ParticleEngine();
engineFire3.setValues( settingFire3 );
engineFire3.initialize();
}
}
engineFire3.initialize();
}  

//Funzione di animazione del fuoco in prima persona
function startFire2(){
if(fp==false){
  startFire();}
else{
if(fire2==true){
  engineFire2.destroy();
fire2=false;
fireSound.pause();}
else{
fire2=true;
if(rain==true){
engineRain.destroy();
engineClouds.destroy();
startRain();}
if(snow==true){
engineSnow.destroy();
startSnow();}

fireSound.play();
engineFire2 = new ParticleEngine();
engineFire2.setValues( settingFire2 );
engineFire2.initialize();
}
engineFire2.initialize();
}  }

//Funzione di animazione della doccia
function startShower(){
 if(fp==true){
 if(shower==true){
  showerAnimation2();
  soundOfShower.pause();
shower=false;    }
else{
  engineShower.destroy();
  soundOfShower.play();
  showerAnimation();
  engineShower = new ParticleEngine();
  engineShower.setValues( settingsShower );
  shower=true;

}}
engineShower.initialize();} 

//Funzione di animazione della neve
function startSnow(){
if(snow==false){
  if(fire==true){
    engineFire.destroy();
    startFire(); }

  if(fire2==true){
    engineFire2.destroy();
    startFire2(); }

snowSound.play();
engineSnow.setValues(snowSettings);
engineSnow.initialize()
//scene.remove(paesaggio);
//scene.add(paesaggioInnevato);
casa.Giardino.remove(base)
casa.Giardino.add(base_Innevata)
casa.Giardino.add(pupazzoDiNeve)
snow=true}

else{
snowSound.pause();
engineSnow.destroy();
//scene.remove(paesaggioInnevato);
//scene.add(paesaggio)
casa.Giardino.remove(base_Innevata)
casa.Giardino.remove(pupazzoDiNeve)
casa.Giardino.add(base);
snow=false}



}

//Funzione di animazione della notte
function startNight(){
    if(night==false){
engineStar.setValues(settingsStar)
engineStar.initialize();  
        night=true;
       nightAnimation1();
       nightSound.play();
    if(fire==true){
engineFire.destroy();
startFire();
    }
       night=true;
       nightAnimation1();
       nightSound.play();
          

    }
    else if(night==true){
        night=false;
        nightAnimation2();
        engineStar.destroy();
        nightSound.pause();
    }
engineStar.initialize();    
}
