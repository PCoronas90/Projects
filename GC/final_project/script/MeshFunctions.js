 //Funzione per la creazione delle mesh in phong material
 function createMesh(geom,imageFile) {
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

//Funzione per la creazione delle mesh in basic material
 function createMesh2(geom,imageFile) {
        var texture = THREE.ImageUtils.loadTexture("assets/textures/general/" + imageFile)
        texture.wrapS = THREE.RepeatWrapping;
        texture.wrapT = THREE.RepeatWrapping;
        geom.computeVertexNormals();

        var mat = new THREE.MeshBasicMaterial();
        mat.map = texture;

        var mesh = new THREE.Mesh(geom, mat);
        mesh.material.map.wrapS = THREE.RepeatWrapping;
        mesh.material.map.wrapT = THREE.RepeatWrapping;
        mesh.material.map.repeat.set(1,1);

        return mesh;
      }

//Funzioni per la generazione delle shape con e senza holes
function drawShapeHole(x1,y1,x2,y2,x3,y3,hole1,hole2) {
       var shape = new THREE.Shape();
       shape.moveTo(0,0);

    shape.lineTo(x1,y1);
    shape.lineTo(x2,y2);
    shape.lineTo(x3,y3);   
        
    shape.holes.push(hole1);
    if(hole2!=null){
    shape.holes.push(hole2);  
    }
    return shape;
      }

function drawShape(x1,y1,x2,y2,x3,y3) {
       var shape = new THREE.Shape();
        shape.moveTo(0,0);
    shape.lineTo(x1,y1);
    shape.lineTo(x2,y2);
    shape.lineTo(x3,y3);
         return shape;
      }

//Creazione delle shape
function asGeomEsterno(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntFinestra(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole1)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomEstFinestra(x1,y1,x2,y2,x3,y3) {           
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole1)).makeGeometry(),'textEst.jpg');
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomInt(x1,y1,x2,y2,x3,y3) {       
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomEst(x1,y1,x2,y2,x3,y3) {           
    shape = createMesh2((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textEst.jpg');
    shape.rotation.x=Math.PI/2 
    return shape};




function asGeomIntPorta1(x1,y1,x2,y2,x3,y3) {      
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole2)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntPorta2(x1,y1,x2,y2,x3,y3) {      
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole3)).makeGeometry(),'textInt.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntFinestraBagno(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole4)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomEstFinestraBagno(x1,y1,x2,y2,x3,y3) {           
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole4)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntPortaBagno(x1,y1,x2,y2,x3,y3) {        
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole5)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntPortaBagno2(x1,y1,x2,y2,x3,y3) {       
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole6)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};


function asGeomOutSoggiorno1(x1,y1,x2,y2,x3,y3) {         
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole7)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntSoggiorno1(x1,y1,x2,y2,x3,y3) {            
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole7)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomOutSoggiorno2(x1,y1,x2,y2,x3,y3) {      
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole8,hole9)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntSoggiorno2(x1,y1,x2,y2,x3,y3) {         
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole10,hole11)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomInt2Soggiorno2(x1,y1,x2,y2,x3,y3) {           
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole12,hole13)).makeGeometry(),'textInt2.jpg');
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomOutSalaPranzo(x1,y1,x2,y2,x3,y3) {            
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole14)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};


function asGeomInSalaPranzo(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole15)).makeGeometry(),'textInt5.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomInSalaPranzo2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt5.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomOutSalaPranzo2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntBagno(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};


function asGeomIntBagno2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntBagno3(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt4.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomInt2Bagno2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole16)).makeGeometry(),'textInt4.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomInt3Bagno2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole17)).makeGeometry(),'textInt3.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntCucina(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole18)).makeGeometry(),'textInt3.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntCucina7(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole18)).makeGeometry(),'textInt5.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};


function asGeomIntCucina2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole19)).makeGeometry(),'textInt3.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomOutCucina2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole19)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomOutCucina3(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole20)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntCucina4(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole21)).makeGeometry(),'textInt3.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntCucina5(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole22)).makeGeometry(),'textInt3.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};

function asGeomIntCucina6(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole23)).makeGeometry(),'textInt5.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomIntPortaStireria1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole24)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};    

function asGeomIntPortaStireria2(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole25)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomIntFinestraStireria1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole26)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};    

function asGeomEstFinestraStireria1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole27)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape}; 

function asGeomInt2Stireria(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomEst2Stireria(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape}; 

function asGeomIntRemove1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole28)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomEstRemove1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole29)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomInt2Remove1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole30)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape}; 

function asGeomInt3Remove1(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole31)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape}; 

function asGeomInt1Scale(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole32)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  

function asGeomInt2Scale(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShape(x1,y1,x2,y2,x3,y3)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape}; 

function asGeomInt3Scale(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh((drawShapeHole(x1,y1,x2,y2,x3,y3,hole34)).makeGeometry(),'textInt2.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};   


function asGeomEst1Scale(x1,y1,x2,y2,x3,y3) {     
    shape = createMesh2((drawShapeHole(x1,y1,x2,y2,x3,y3,hole33)).makeGeometry(),'textEst.jpg');    
    shape.rotation.x=Math.PI/2 
    return shape};  


//Definizione degli holes
var hole1 = new THREE.Path([new THREE.Vector2(1.5,0.95),new THREE.Vector2(2.7,0.95),new THREE.Vector2(2.7,2.35),
      new THREE.Vector2(1.5,2.35)]);

var hole2 = new THREE.Path([new THREE.Vector2(2.8,0),new THREE.Vector2(3.9,0),new THREE.Vector2(3.9,2.4),
      new THREE.Vector2(2.8,2.4)]);

var hole3 = new THREE.Path([new THREE.Vector2(0.3,0),new THREE.Vector2(1.4,0),new THREE.Vector2(1.4,2.4),
      new THREE.Vector2(0.3,2.4)]);

var hole4 = new THREE.Path([new THREE.Vector2(1.58,2.4),new THREE.Vector2(0.49,2.4),new THREE.Vector2(0.49,1),
      new THREE.Vector2(1.58,1)]);

 var hole5 = new THREE.Path([new THREE.Vector2(1.51,2.41),new THREE.Vector2(0.40,2.41),new THREE.Vector2(0.40,0),
      new THREE.Vector2(1.51,0)]);


 var hole6 = new THREE.Path([new THREE.Vector2(1.6,2.41),new THREE.Vector2(0.20,2.41),new THREE.Vector2(0.20,0),
      new THREE.Vector2(1.6,0)]);

var hole7 = new THREE.Path([new THREE.Vector2(2.65,2.40),new THREE.Vector2(0.85,2.40),new THREE.Vector2(0.85,0.1),
      new THREE.Vector2(2.65,0.1)]);

var hole8 = new THREE.Path([new THREE.Vector2(2.5,2.40),new THREE.Vector2(0.4,2.40),new THREE.Vector2(0.4,0.1),
      new THREE.Vector2(2.5,0.1)]);

var hole9 = new THREE.Path([new THREE.Vector2(5.4,2.40),new THREE.Vector2(3.3,2.40),new THREE.Vector2(3.3,0.1),
      new THREE.Vector2(5.4,0.1)]);

var hole10 = new THREE.Path([new THREE.Vector2(2.3,2.8),new THREE.Vector2(0.31,2.8),new THREE.Vector2(0.31,0.1),
      new THREE.Vector2(2.3,0.1)]);

var hole11 = new THREE.Path([new THREE.Vector2(5.2,2.8),new THREE.Vector2(3.2,2.8),new THREE.Vector2(3.2,0.1),
      new THREE.Vector2(5.2,0.1)]);

var hole12 = new THREE.Path([new THREE.Vector2(2.3,2.4),new THREE.Vector2(0.31,2.4),new THREE.Vector2(0.31,0.1),
      new THREE.Vector2(2.3,0.1)]);

var hole13 = new THREE.Path([new THREE.Vector2(5.2,2.4),new THREE.Vector2(3.2,2.4),new THREE.Vector2(3.2,0.1),
      new THREE.Vector2(5.2,0.1)]);     

var hole14 = new THREE.Path([new THREE.Vector2(3.2,2.4),new THREE.Vector2(1.1,2.4),new THREE.Vector2(1.1,0.1),
      new THREE.Vector2(3.2,0.1)]);  

var hole15 = new THREE.Path([new THREE.Vector2(3.1,2.4),new THREE.Vector2(1,2.4),new THREE.Vector2(1,0.1),
      new THREE.Vector2(3.1,0.1)]);
//
var hole16 = new THREE.Path([new THREE.Vector2(2.6,2.4),new THREE.Vector2(1.4,2.4),new THREE.Vector2(1.4,0.1),
      new THREE.Vector2(2.6,0.1)]);

var hole17 = new THREE.Path([new THREE.Vector2(1.6,2.4),new THREE.Vector2(0.4,2.4),new THREE.Vector2(0.4,0.1),
      new THREE.Vector2(1.6,0.1)]);

var hole18 = new THREE.Path([new THREE.Vector2(2.2,2.4),new THREE.Vector2(1.1,2.4),new THREE.Vector2(1.1,0.1),
      new THREE.Vector2(2.2,0.1)]);

var hole19 = new THREE.Path([new THREE.Vector2(3.1,2.4),new THREE.Vector2(2.0,2.4),new THREE.Vector2(2.0,0.1),
      new THREE.Vector2(3.1,0.1)]);

var hole20 = new THREE.Path([new THREE.Vector2(2.65,2.4),new THREE.Vector2(1.52,2.4),new THREE.Vector2(1.52,1.09),
      new THREE.Vector2(2.65,1.09)]);
//
var hole21 = new THREE.Path([new THREE.Vector2(2.7,2.4),new THREE.Vector2(1.53,2.4),new THREE.Vector2(1.53,1),
      new THREE.Vector2(2.7,1)]);

var hole22 = new THREE.Path([new THREE.Vector2(3.9,2.4),new THREE.Vector2(2.60,2.4),new THREE.Vector2(2.60,0),
      new THREE.Vector2(3.9,0)]);

var hole23 = new THREE.Path([new THREE.Vector2(1.6,2.4),new THREE.Vector2(0.27,2.4),new THREE.Vector2(0.27,0),
      new THREE.Vector2(1.6,0)]);

var hole24 = new THREE.Path([new THREE.Vector2(2.5,2.4),new THREE.Vector2(1.4,2.4),new THREE.Vector2(1.4,0.1),
      new THREE.Vector2(2.5,0.1)]);

var hole25 = new THREE.Path([new THREE.Vector2(1.7,2.4),new THREE.Vector2(0.7,2.4),new THREE.Vector2(0.7,0.1),
      new THREE.Vector2(1.7,0.1)]);

var hole26 = new THREE.Path([new THREE.Vector2(2.62,2.4),new THREE.Vector2(1.4,2.4),new THREE.Vector2(1.4,1),
      new THREE.Vector2(2.62,1)]);

var hole27 = new THREE.Path([new THREE.Vector2(1.67,2.4),new THREE.Vector2(0.525,2.4),new THREE.Vector2(0.525,1),
      new THREE.Vector2(1.67,1)]);

var hole28 = new THREE.Path([new THREE.Vector2(2.5,2.4),new THREE.Vector2(1.2,2.4),new THREE.Vector2(1.2,1),
      new THREE.Vector2(2.5,1)]);

var hole29 = new THREE.Path([new THREE.Vector2(2.1,2.4),new THREE.Vector2(0.8,2.4),new THREE.Vector2(0.8,1),
      new THREE.Vector2(2.1,1)]);

var hole30 = new THREE.Path([new THREE.Vector2(2.2,2.4),new THREE.Vector2(1,2.4),new THREE.Vector2(1,0),
      new THREE.Vector2(2.2,0)]);

var hole31 = new THREE.Path([new THREE.Vector2(1.3,2.4),new THREE.Vector2(0.15,2.4),new THREE.Vector2(0.15,0.1),
      new THREE.Vector2(1.3,0.1)]);

var hole32 = new THREE.Path([new THREE.Vector2(2.45,2.4),new THREE.Vector2(1.25,2.4), new THREE.Vector2(1.25,1),
      new THREE.Vector2(2.45,1)]);

var hole33 = new THREE.Path([new THREE.Vector2(2.35,2.4),new THREE.Vector2(1.15,2.4),new THREE.Vector2(1.15,1),
      new THREE.Vector2(2.35,1)]);       

var hole34 = new THREE.Path([new THREE.Vector2(1.41,2.4),new THREE.Vector2(0.2,2.4),new THREE.Vector2(0.2,0),
      new THREE.Vector2(1.41,0)]);













