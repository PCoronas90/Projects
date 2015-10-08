//Definizione delle shape dei muri delle varie stanze. Sono in un Object3d in modo da essere utilizzat
//Nella funzione di caricamento nello script HomeCreator.

var shapeCamera01=new THREE.Object3D();

      shapeInFin1=asGeomIntFinestra(0,2.75,4.2,2.75,4.2,0)
      shapeOutFin1=asGeomEstFinestra(0,2.75,4.21,2.75,4.21,0)
      shapeInFin1.position.set(4.2,0.11,0.05);
      shapeInFin1.rotation.y=Math.PI
      shapeOutFin1.position.set(0,-0.01,0.05);

      shapeIn1=asGeomInt(3.2,0,3.2,2.7,0,2.7)
      shapeIn1.position.set(0.11,0,0.1);
      shapeIn1.rotation.y=Math.PI/2

      shapeOut1=asGeomEst(3.2,0,3.2,2.8,0,2.8)
      shapeOut1.position.set(-0.009,3.18,0);
      shapeOut1.rotation.y=-Math.PI/2

      shapeIn12=asGeomInt(3.18,0,3.18,2.8,0,2.8)
      shapeIn12.position.set(4.09,3.18,0);
      shapeIn12.rotation.y=-Math.PI/2

      shapeIn13=asGeomInt(2.7,0,2.7,2.8,0,2.8)
      shapeIn13.position.set(4.22,0.6,0);
      shapeIn13.rotation.y=Math.PI/2

      shapeIn14=asGeomIntPorta1(0,2.8,4.2,2.8,4.2,0)
      shapeIn14.position.set(0,3.09,0);

      shapeIn15=asGeomIntPorta2(0,2.8,4.15,2.8,4.15,0)
      shapeIn15.position.set(4.2,3.21,0);
      shapeIn15.rotation.y=Math.PI

      shapeIn16=asGeomEst(0,2.8,0.5,2.8,0.5,0)
      shapeIn16.rotation.y=Math.PI/2
      shapeIn16.position.set(4.21,-0.01,0);

      shapeIn17 = createMesh2(new THREE.BoxGeometry(0.5,1.8,2.8), 'textEst.jpg');
      shapeIn17.material.map.wrapS = THREE.RepeatWrapping;
      shapeIn17.material.map.wrapT = THREE.RepeatWrapping;
      shapeIn17.material.map.repeat.set(1,3,3);
      shapeIn17.material.map.needszzUpdate = true;
      shapeIn17.position.set(0.25,-0.9,1.4);

shapeCamera01.add(shapeInFin1)
shapeCamera01.add(shapeOutFin1)
shapeCamera01.add(shapeIn1)
shapeCamera01.add(shapeOut1)
shapeCamera01.add(shapeIn12)
shapeCamera01.add(shapeIn13)
shapeCamera01.add(shapeIn14)
shapeCamera01.add(shapeIn15)
shapeCamera01.add(shapeIn16)
shapeCamera01.add(shapeIn17)

var shapeBagno01=new THREE.Object3D();
      shapeIn3=asGeomEstFinestraBagno(0,2.8,1.9,2.8,1.9,0)
      shapeIn3.position.set(-0.011,1.975,0);
      shapeIn3.rotation.y=-Math.PI/2

      shapeOut3=asGeomIntFinestraBagno(0,2.80,1.9,2.80,1.9,0)
      shapeOut3.position.set(0.11,-0.09,0.0);
      shapeOut3.rotation.y=Math.PI/2

      shapeIn31=asGeomIntPortaBagno(0,2.80,1.8,2.80,1.8,0)
      shapeIn31.position.set(2.29,1.9,0);
      shapeIn31.rotation.y=-Math.PI/2

      shapeIn32=asGeomIntPortaBagno(0,2.80,1.8,2.80,1.8,0)
      shapeIn32.position.set(2.41,0,0);
      shapeIn32.rotation.y=Math.PI/2

      shapeIn33=asGeomIntPortaBagno2(0,2.80,1.8,2.80,1.8,0)
      shapeIn33.position.set(4.085,1.8,0);
      shapeIn33.rotation.y=-Math.PI/2

      shapeIn34=asGeomIntPortaBagno2(0,2.80,1.8,2.80,1.8,0)
      shapeIn34.position.set(4.2209,0,0);
      shapeIn34.rotation.y=Math.PI/2;

shapeBagno01.add(shapeIn3);
shapeBagno01.add(shapeOut3)
shapeBagno01.add(shapeIn31)
shapeBagno01.add(shapeIn32)
shapeBagno01.add(shapeIn33)
shapeBagno01.add(shapeIn34)

var shapeStireria=new THREE.Object3D();
      shapeIn9=asGeomIntPortaStireria1(0,2.80,2.5,2.80,2.5,0)
      shapeIn9.position.set(0,-0.01,0);

      shapeIn91=asGeomIntPortaStireria2(0,2.80,3.1,2.80,3.1,0)
      shapeIn91.rotation.y=Math.PI
      shapeIn91.position.set(3.1,0.12,0);


      shapeIn92=asGeomIntFinestraStireria1(0,2.80,3.1,2.80,3.1,0)
      shapeIn92.position.set(0,2.19,0);

      shapeIn93=asGeomEstFinestraStireria1(0,2.80,3.1,2.80,3.1,0)
      shapeIn93.rotation.y=Math.PI
      shapeIn93.position.set(3.1,2.31,0);

      shapeIn94=asGeomInt2Stireria(0,2.80,2.3,2.80,2.3,0)
      shapeIn94.rotation.y=Math.PI/2
      shapeIn94.position.set(0.11,0,0);

      shapeIn95=asGeomEst2Stireria(0,2.80,2.3,2.80,2.3,0)
      shapeIn95.rotation.y=Math.PI/2
      shapeIn95.position.set(3.11,0,0);

      shapeIn96=asGeomInt2Stireria(0,2.80,2.31,2.80,2.31,0)
      shapeIn96.rotation.y=-Math.PI/2
      shapeIn96.position.set(-0.01,2.3,0);

      shapeIn97=asGeomInt2Stireria(0,2.80,2.3,2.80,2.3,0)
      shapeIn97.rotation.y=-Math.PI/2
      shapeIn97.position.set(2.98,2.3,0);

      var shapeIn98 = createMesh2(new THREE.BoxGeometry(6.45,1.65,0.2), 'text1.jpg');
      shapeIn98.material.map.wrapS = THREE.RepeatWrapping;
      shapeIn98.material.map.wrapT = THREE.RepeatWrapping;
      shapeIn98.material.map.repeat.set(8,2);
      shapeIn98.material.map.needsUpdate = true;
      shapeIn98.position.set(-0.12,2.3,0);

shapeStireria.add(shapeIn9)
shapeStireria.add(shapeIn91)
shapeStireria.add(shapeIn92)
shapeStireria.add(shapeIn93)
shapeStireria.add(shapeIn94)
shapeStireria.add(shapeIn95)
shapeStireria.add(shapeIn96)
shapeStireria.add(shapeIn97)
shapeStireria.add(shapeIn98)

var shapeCucina= new THREE.Object3D();

      shapeIn8=asGeomIntCucina7(0,2.80,4.2,2.80,4.2,0)
      shapeIn8.position.set(0,-0.01,0);

      shapeIn81=asGeomIntCucina2(0,2.80,4.2,2.80,4.2,0)
      shapeIn81.rotation.y=Math.PI
      shapeIn81.position.set(4.2,0.11,0);

      shapeIn82=asGeomOutCucina2(0,2.80,4.2,2.80,4.2,0)
      shapeIn82.rotation.y=Math.PI
      shapeIn82.position.set(4.2,4.21,0);

      shapeIn83=asGeomIntCucina(0,2.80,4.2,2.80,4.2,0)
      shapeIn83.position.set(0,4.09,0);

      shapeIn84=asGeomOutCucina3(0,2.80,4.2,2.80,4.2,0)
      shapeIn84.rotation.y=Math.PI/2
      shapeIn84.position.set(4.21,0,0);

      shapeIn85=asGeomIntCucina4(0,2.80,4.2,2.80,4.2,0)
      shapeIn85.rotation.y=-Math.PI/2
      shapeIn85.position.set(4.08,4.21,0);

      shapeIn86=asGeomIntCucina5(0,2.80,4.2,2.80,4.2,0)
      shapeIn86.rotation.y=Math.PI/2
      shapeIn86.position.set(0.11,0,0);

      shapeIn87=asGeomIntCucina6(0,2.80,4.2,2.80,4.2,0)
      shapeIn87.rotation.y=-Math.PI/2
      shapeIn87.position.set(-0.01,4.18,0);

      var shapeIn88 = createMesh2(new THREE.BoxGeometry(3.5,3,0.2), 'text1.jpg');
      shapeIn88.material.map.wrapS = THREE.RepeatWrapping;
      shapeIn88.material.map.wrapT = THREE.RepeatWrapping;
      shapeIn88.material.map.repeat.set(8,6);
      shapeIn88.material.map.needsUpdate = true;
      shapeIn88.position.set(2.45,5.72,0);


shapeCucina.add(shapeIn8)
shapeCucina.add(shapeIn81)
shapeCucina.add(shapeIn82)
shapeCucina.add(shapeIn83)
shapeCucina.add(shapeIn84)
shapeCucina.add(shapeIn85)
shapeCucina.add(shapeIn86)
shapeCucina.add(shapeIn87)
shapeCucina.add(shapeIn88)

var shapeCamera02= new THREE.Object3D();

      shapeInFin2=asGeomIntFinestra(0,2.75,4.2,2.75,4.2,0)
      shapeOutFin2=asGeomEstFinestra(0,2.75,4.2,2.75,4.2,0)
      shapeInFin2.position.set(0,3.09,0.05);
      shapeOutFin2.position.set(4.2,3.21,0.05);
      shapeOutFin2.rotation.y=Math.PI

      shapeIn2=asGeomInt(3.2,0,3.2,2.7,0,2.7)
      shapeIn2.position.set(0.11,0,0.1);
      shapeIn2.rotation.y=Math.PI/2

      shapeOut2=asGeomEst(3.2,0,3.2,2.8,0,2.8)
      shapeOut2.position.set(-0.009,3.21,0);
      shapeOut2.rotation.y=-Math.PI/2

      shapeIn22=asGeomInt(3.18,0,3.18,2.8,0,2.8)
      shapeIn22.position.set(4.09,3.18,0);
      shapeIn22.rotation.y=-Math.PI/2

      shapeIn23=asGeomInt(3.38,0,3.38,2.8,0,2.8)
      shapeIn23.position.set(4.22,-0.19,0);
      shapeIn23.rotation.y=Math.PI/2

      shapeIn24=asGeomIntPorta1(0,2.8,4.15,2.8,4.15,0)
      shapeIn24.position.set(0,-0.01,0);

      shapeIn25=asGeomIntPorta2(0,2.8,4.15,2.8,4.15,0)
      shapeIn25.position.set(4.2,0.11,0);
      shapeIn25.rotation.y=Math.PI


shapeCamera02.add(shapeInFin2)
shapeCamera02.add(shapeOutFin2)
shapeCamera02.add( shapeIn2)
shapeCamera02.add(shapeOut2)
shapeCamera02.add(shapeIn22)
shapeCamera02.add(shapeIn23)
shapeCamera02.add(shapeIn24)
shapeCamera02.add(shapeIn25)

var shapeBagno02= new THREE.Object3D();
      shapeIn7=asGeomInt(0,2.80,3,2.80,3,0)
      shapeIn7.position.set(0,-0.01,0);

      shapeIn71=asGeomIntBagno3(0,2.80,3,2.80,3,0)
      shapeIn71.rotation.y=Math.PI
      shapeIn71.position.set(3,0.11,0);

      shapeIn72=asGeomIntBagno3(0,2.80,1.4,2.80,1.4,0)
      shapeIn72.rotation.y=Math.PI/2
      shapeIn72.position.set(0.11,0,0);

      shapeIn73=asGeomInt(0,2.80,1.4,2.80,1.4,0)
      shapeIn73.rotation.y=-Math.PI/2
      shapeIn73.position.set(-0.01,1.4,0);

      shapeIn74=asGeomIntBagno3(0,2.80,1.4,2.80,1.4,0)
      shapeIn74.rotation.y=Math.PI/2
      shapeIn74.position.set(3.11,0,0);

      shapeIn75=asGeomIntBagno3(0,2.80,1.4,2.80,1.4,0)
      shapeIn75.rotation.y=-Math.PI/2
      shapeIn75.position.set(2.88,1.4,0);

      shapeIn76=asGeomInt2Bagno2(0,2.80,3,2.80,3,0)
      shapeIn76.position.set(0,1.29,0);

      shapeIn77=asGeomInt3Bagno2(0,2.80,3,2.80,3,0)
      shapeIn77.rotation.y=Math.PI
      shapeIn77.position.set(3,1.41,0);

shapeBagno02.add(shapeIn7)
shapeBagno02.add( shapeIn71)
shapeBagno02.add( shapeIn72)
shapeBagno02.add(shapeIn73)
//shapeBagno02.add(shapeIn74)
shapeBagno02.add(shapeIn75)
shapeBagno02.add(shapeIn76)
shapeBagno02.add(shapeIn77)

var shapeScale=new THREE.Object3D();

      shapeInS1=asGeomInt1Scale(0,2.80,3.6,2.80,3.6,0)
      shapeInS1.position.set(0,1.49,0);

      shapeInS2=asGeomEst1Scale(0,2.80,3.6,2.80,3.6,0)
      shapeInS2.rotation.y=Math.PI
      shapeInS2.position.set(3.6,1.61,0);

      shapeInS3=asGeomInt2Scale(0,2.80,3.6,2.80,3.6,0)
      shapeInS3.position.set(0,-0.01,0);

      shapeInS4=asGeomInt2Scale(0,2.80,3.6,2.80,3.6,0)
      shapeInS4.rotation.y=Math.PI
      shapeInS4.position.set(3.6,0.11,0);

      shapeInS5=asGeomInt3Scale(0,2.80,1.55,2.80,1.55,0)
      shapeInS5.rotation.y=-Math.PI/2
      shapeInS5.position.set(3.48,1.575,0);

      shapeInS6=asGeomInt3Scale(0,2.80,1.6,2.80,1.6,0)
      shapeInS6.rotation.y=Math.PI/2
      shapeInS6.position.set(3.61,0,0);

      var shapeInS7 = createMesh2(new THREE.BoxGeometry(7.5,4.85,0.2), 'text1.jpg');
      shapeInS7.material.map.wrapS = THREE.RepeatWrapping;
      shapeInS7.material.map.wrapT = THREE.RepeatWrapping;
      shapeInS7.material.map.repeat.set(8,6);
      shapeInS7.material.map.needsUpdate = true;
      shapeInS7.position.set(-0.4,2.3,0);

shapeScale.add(shapeInS1)
shapeScale.add(shapeInS2)
shapeScale.add(shapeInS3)
shapeScale.add(shapeInS4)
shapeScale.add(shapeInS5)
shapeScale.add(shapeInS6)
shapeScale.add(shapeInS7)

var shapeSoggiorno01= new THREE.Object3D();
      shapeOut4=asGeomOutSoggiorno1(0,2.80,3.5,2.80,3.5,0)
      shapeOut4.position.set(0,-0.01,0.00);

      shapeIn4=asGeomIntSoggiorno1(0,2.80,3.5,2.80,3.5,0)
      shapeIn4.rotation.y=-Math.PI
      shapeIn4.position.set(3.5,0.11,0.00);

      var shapeIn41 = createMesh(new THREE.BoxGeometry(4,1,0.2), 'textEst2.jpg');
     shapeIn41.material.map.wrapS = THREE.RepeatWrapping;
      shapeIn41.material.map.wrapT = THREE.RepeatWrapping;
      shapeIn41.material.map.repeat.set(4,1);
      shapeIn41.material.map.needsUpdate = true;      
      shapeIn41.position.set(2,0,0);

shapeSoggiorno01.add(shapeIn4)
shapeSoggiorno01.add(shapeOut4)
shapeSoggiorno01.add(shapeIn41) 

var shapeSoggiorno02=new THREE.Object3D();
      shapeOut5=asGeomOutSoggiorno2(0,2.80,5.61,2.80,5.61,0)
      shapeOut5.position.set(0,-0.01,0.00);

      shapeIn5=asGeomIntSoggiorno2(0,2.80,5.6,2.80,5.6,0)
      shapeIn5.rotation.y=-Math.PI
      shapeIn5.position.set(5.6,0.515,0.00);

      shapeIn51=asGeomInt2Soggiorno2(0,2.80,5.6,2.80,5.6,0)
      shapeIn51.rotation.y=-Math.PI
      shapeIn51.position.set(5.6,0.11,0.00);


      shapeIn52=asGeomEst(0,2.8,0.5,2.8,0.5,0)
      shapeIn52.rotation.y=-Math.PI/2
      shapeIn52.position.set(-0.009,0.49,0);

      shapeIn53=asGeomEst(0,2.8,0.7,2.8,0.7,0)
      shapeIn53.rotation.y=Math.PI/2
      shapeIn53.position.set(5.609,-0.02,0);

      shapeIn54=asGeomInt(0,2.8,0.5,2.8,0.5,0)
      shapeIn54.rotation.y=-Math.PI/2
      shapeIn54.position.set(2.38,0.49,0);

      shapeIn55=asGeomInt(0,2.8,0.5,2.8,0.5,0)
      shapeIn55.rotation.y=-Math.PI/2
      shapeIn55.position.set(5.29,0.49,0);

      shapeIn56=asGeomInt(0,2.8,0.5,2.8,0.5,0)
      shapeIn56.rotation.y=Math.PI/2
      shapeIn56.position.set(0.41,-0.01,0);

      shapeIn57=asGeomInt(0,2.8,0.5,2.8,0.5,0)
      shapeIn57.rotation.y=Math.PI/2
      shapeIn57.position.set(3.31,-0.01,0);

shapeSoggiorno02.add(shapeOut5)
shapeSoggiorno02.add(shapeIn5)
shapeSoggiorno02.add(shapeIn51)
shapeSoggiorno02.add( shapeIn52)
shapeSoggiorno02.add(shapeIn53)
shapeSoggiorno02.add(shapeIn54)
shapeSoggiorno02.add(shapeIn55)
shapeSoggiorno02.add(shapeIn56)
shapeSoggiorno02.add(shapeIn57) 

var shapeSalaPranzo= new THREE.Object3D();

      shapeOut6=asGeomOutSalaPranzo(0,2.80,4.215,2.80,4.215,0)
      shapeOut6.position.set(0,-0.009,0.00);
      shapeIn6=asGeomInSalaPranzo(0,2.80,4.2,2.80,4.2,0)
      shapeIn6.rotation.y=-Math.PI
      shapeIn6.position.set(4.2,0.11,0.00);

      shapeIn61=asGeomInSalaPranzo2(0,2.80,3.7,2.80,3.7,0)
      shapeIn61.rotation.y=-Math.PI/2
      shapeIn61.position.set(4.09,3.7,0.00);

      shapeOut61=asGeomOutSalaPranzo2(0,2.80,3.7,2.80,3.7,0)
      shapeOut61.rotation.y=Math.PI/2
      shapeOut61.position.set(4.208,0.0,0.00);

      var  shapeOut62 = createMesh(new THREE.BoxGeometry(4.2,1,0.2), 'textEst2.jpg');
      shapeOut62.material.map.wrapS = THREE.RepeatWrapping;
      shapeOut62.material.map.wrapT = THREE.RepeatWrapping;
      shapeOut62.material.map.repeat.set(5,1);
      shapeOut62.material.map.needsUpdate = true;
      shapeOut62.position.set(2.1,0,0);

shapeSalaPranzo.add(shapeOut6)
shapeSalaPranzo.add(shapeIn6)
shapeSalaPranzo.add(shapeIn61)
shapeSalaPranzo.add(shapeOut61)
shapeSalaPranzo.add(shapeOut62) 

var shapeRemove02= new THREE.Object3D();

      shapeInR1=asGeomIntRemove1(0,2.80,3.3,2.80,3.3,0)      
      shapeInR1.position.set(0,5.08,0.00);

      shapeOutR1=asGeomEstRemove1(0,2.80,3.3,2.80,3.3,0)  
      shapeOutR1.rotation.y=Math.PI    
      shapeOutR1.position.set(3.3,5.21,0.00);

      shapeInR2=asGeomInt2Remove1(0,2.80,2.2,2.80,2.2,0)  
      shapeInR2.rotation.y=Math.PI/2    
      shapeInR2.position.set(0.11,2.9,0.00);

      shapeInR3=asGeomInt3Remove1(0,2.80,2.4,2.80,2.4,0)  
      shapeInR3.rotation.y=-Math.PI/2    
      shapeInR3.position.set(-0.01,5.25,0.00);


shapeRemove02.add(shapeOutR1)
shapeRemove02.add( shapeInR1)
shapeRemove02.add(shapeInR2)
shapeRemove02.add(shapeInR3)