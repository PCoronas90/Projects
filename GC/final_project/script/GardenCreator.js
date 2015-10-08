//Definizione della piattaforma piastrellata esterna
  function GardenCreator(){
  Giardino = new THREE.Object3D();
  var ext1 = createMesh2(new THREE.BoxGeometry(4.65,6,0.2), 'text1.jpg');
  ext1.material.map.wrapS = THREE.RepeatWrapping;
  ext1.material.map.wrapT = THREE.RepeatWrapping;
  ext1.material.map.repeat.set(6.8,12);
  ext1.material.map.needsUpdate = true;
  ext1.position.set(15.1,-5.8,0);
  Giardino.add(ext1);

  var ext2 = createMesh2(new THREE.BoxGeometry(17.40,2.8,0.2), 'text1.jpg');
  ext2.material.map.wrapS = THREE.RepeatWrapping;
  ext2.material.map.wrapT = THREE.RepeatWrapping;
  ext2.material.map.repeat.set(14.7*2,2.8*2);
  ext2.material.map.needsUpdate = true;
  ext2.position.set(8.71,-1.4,0);
  Giardino.add(ext2);


  var ext3 = createMesh2(new THREE.BoxGeometry(12.72,0.2,0.2), 'textEst2.jpg');
  ext3.material.map.wrapS = THREE.RepeatWrapping;
  ext3.material.map.wrapT = THREE.RepeatWrapping;
  ext3.material.map.repeat.set(14.7*2,1);
  ext3.material.map.needsUpdate = true;
  ext3.position.set(6.4,-2.9,0);
  Giardino.add(ext3);

  var ext4 = createMesh2(new THREE.BoxGeometry(0.2,5.8,0.2), 'textEst2.jpg');
  ext4.material.map.wrapS = THREE.RepeatWrapping;
  ext4.material.map.wrapT = THREE.RepeatWrapping;
  ext4.material.map.repeat.set(1,14.7*2);
  ext4.material.map.needsUpdate = true;
  ext4.position.set(12.68,-5.9,0);
  Giardino.add(ext4);

  var ext5 = createMesh2(new THREE.BoxGeometry(4.85,0.2,0.2), 'textEst2.jpg');
  ext5.material.map.wrapS = THREE.RepeatWrapping;
  ext5.material.map.wrapT = THREE.RepeatWrapping;
  ext5.material.map.repeat.set(10*2,1);
  ext5.material.map.needsUpdate = true;
  ext5.position.set(15,-8.89,0);
  Giardino.add(ext5);

  var ext9 = createMesh2(new THREE.BoxGeometry(17.40,0.2,0.2), 'textEst2.jpg');
  ext9.material.map.wrapS = THREE.RepeatWrapping;
  ext9.material.map.wrapT = THREE.RepeatWrapping;
  ext9.material.map.repeat.set(10*2,1);
  ext9.material.map.needsUpdate = true;
  ext9.position.set(8.71,11.42,0);
  Giardino.add(ext9);

//Definizione delle colonne esterne
  var Colonna1 = createMesh2(new THREE.BoxGeometry(0.5,0.5,2.8), 'textEst.jpg');
  Colonna1.material.map.wrapS = THREE.RepeatWrapping;
  Colonna1.material.map.wrapT = THREE.RepeatWrapping;
  Colonna1.material.map.repeat.set(1,1,4);
  Colonna1.material.map.needsUpdate = true;
  Colonna1.position.set(4,-1,1.4);
  Giardino.add(Colonna1);

  var Colonna2 = createMesh2(new THREE.BoxGeometry(0.5,0.5,2.8), 'textEst.jpg');
  Colonna2.material.map.wrapS = THREE.RepeatWrapping;
  Colonna2.material.map.wrapT = THREE.RepeatWrapping;
  Colonna2.material.map.repeat.set(1,1,4);
  Colonna2.material.map.needsUpdate = true;
  Colonna2.position.set(7.5,-1,1.4);
  Giardino.add(Colonna2);

  var Colonna3 = createMesh2(new THREE.BoxGeometry(0.5,0.5,2.8), 'textEst.jpg');
  Colonna3.material.map.wrapS = THREE.RepeatWrapping;
  Colonna3.material.map.wrapT = THREE.RepeatWrapping;
  Colonna3.material.map.repeat.set(1,1,4);
  Colonna3.material.map.needsUpdate = true;
  Colonna3.position.set(10.5,-1,1.4);
  Giardino.add(Colonna3);

  var Colonna4 = createMesh2(new THREE.BoxGeometry(0.5,0.5,2.8), 'textEst.jpg');
  Colonna4.material.map.wrapS = THREE.RepeatWrapping;
  Colonna4.material.map.wrapT = THREE.RepeatWrapping;
  Colonna4.material.map.repeat.set(1,1,4);
  Colonna4.material.map.needsUpdate = true;
  Colonna4.position.set(13.5,-1,1.4);
  Giardino.add(Colonna4);

//Definizione della base della casa
  base = createMesh2(new THREE.BoxGeometry(40,40,0.2), 'Giardino.jpg'); 
  base.position.set(10,0,-0.1);
  Giardino.add(base);
  Giardino.base=base;

//Definizione della base innevata
  
  base_Innevata = createMesh2(new THREE.BoxGeometry(40,40,0.2), 'neve_0.jpg');
  base_Innevata.position.set(10,0,-0.1);
  pupazzoDiNeve=createObject('bonecoDeNeve.obj','bonecoDeNeve.mtl',0.02,0.02,0.02,10,-5,0.6);

//Alberi,aumentano troppo il consumo di risorse
//albero1=createObject('blackTupelo.obj','blackTupelo.mtl',0.3,0.3,0.3,-5,-10,0.2, Math.PI/1.5)
//albero2=createObject('blackTupelo.obj','blackTupelo.mtl',0.3,0.3,0.3,-5,10,0.2, Math.PI/1.5)
//albero3=createObject('quakingAspen.obj','quakingAspen.mtl',0.3,0.3,0.3,15,14,0.2, Math.PI/1.5)
//Giardino.add(albero1)
//Giardino.add(albero2)
//Giardino.add(albero3)




      Giardino.scale.x=2
        Giardino.scale.y=2
       Giardino.scale.z=2






  return Giardino;


}