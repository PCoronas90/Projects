//Funzione per caricare file audio
var CreateSound = function(src, volume,  loop) {
	var audio = document.createElement('audio');
	var source = document.createElement('source');
	source.src = 'assets/sounds/' + src;
	audio.appendChild(source);
	this.position = new THREE.Vector3();
	audio.volume = volume;
	audio.loop = loop;
	this.play = function() {
		audio.play();}
	this.pause = function() {
		audio.pause();	}
	}

//Definizione di oggetti audio
var soundOfShower = new CreateSound('Shower_sound.mp3', 1, true);
var soundOfRain = new CreateSound('Rain_Sound.mp3', 1, true);
var portSound_open=new CreateSound('Port_open.mp3', 1, false);
var portSound_close=new CreateSound('Port_close.mp3', 1, false);
var portaScorrevoleSound=new CreateSound('Porta_scorrevole.mp3', 1, false);
var finestroneSound=new CreateSound('Finestrone_sound.mp3', 1, false);
var fireSound=new CreateSound('Fire_sound.mp3', 1,true);
var music=new CreateSound('Eiffel_65_Blue.mp3', 1,true);
var click=new CreateSound('click.mp3', 1,false);
var nightSound=new CreateSound('night.mp3', 1,false);
var snowSound=new CreateSound('snow.mp3', 1,false);
var fireKitchenSound=new CreateSound('fire-1.mp3', 0.6,true);
var esplosione=new CreateSound('esplosione.mp3', 0.8,false);