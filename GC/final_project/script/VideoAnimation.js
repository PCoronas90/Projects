
function createVideo(){
var filmato= new THREE.Object3D();
var texture;
var $video = $('#video');
var video = $video[0];
video.pause();
var showVideo=false;

 
    
      
        texture = new THREE.Texture(video);
        texture.minFilter = THREE.LinearFilter;
        texture.magFilter = THREE.LinearFilter;
        texture.format = THREE.RGBFormat;
        texture.generateMipmaps = false;
        texture.needsUpdate=false;

function createCubeVideo(){
  var Video;
       Video = createMesh22(new THREE.BoxGeometry(1.2,0.79,0.025));
        Video.rotation.x=Math.PI/2;
       Video.rotation.y=Math.PI/1.5;
        
        
       Video.interact=function(){
          if(showVideo==false){
            Video.material.materials[4].map = texture;
            showVideo=true;
            video.volume=1;
            video.play();
          }
          else if(showVideo==true){
          Video.material.materials[4].map = THREE.ImageUtils.loadTexture("assets/textures/general/schermoNero.jpg");
          showVideo=false;
          video.volume=0;
          //video.pause();
        }}



        
        
        
        return Video;}

       Video=createCubeVideo();

filmato.add(Video);
filmato.add(video);
filmato.add($video);

filmato.Video=Video;
filmato.video=video;
filmato.$video=$video;
filmato.texture=texture;
filmato.showVideo=showVideo;



        

        function createMesh22 (geom) {
          var materialArray = [];
          materialArray.push(new THREE.MeshBasicMaterial({ color: 0x555555  }));
          materialArray.push(new THREE.MeshBasicMaterial({ color: 0x555555  }));
          materialArray.push(new THREE.MeshBasicMaterial({ color: 0x555555  }));
          materialArray.push(new THREE.MeshBasicMaterial({ color: 0x555555   }));
          materialArray.push(new THREE.MeshBasicMaterial({ map: THREE.ImageUtils.loadTexture("assets/textures/general/schermoNero.jpg") }));
          materialArray.push(new THREE.MeshBasicMaterial({ color: 0xeeee33  }));
          var faceMaterial = new THREE.MeshFaceMaterial(materialArray);

          // create a multimaterial
          var mesh = new THREE.Mesh(geom, faceMaterial);

          return mesh;
        }
return filmato;
     
}