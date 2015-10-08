from pyplasm import *
import os,sys
sys.path.insert(0, 'lib/py/')
from lar2psm import *
from larcc import *
from sysml import *

#Funzioni Utili
DRAW = COMP([VIEW,STRUCT,MKPOLS])
DRAW2 = COMP([STRUCT,MKPOLS])

def rgbToPlasmColor(color):
	return [color[0]/255., color[1]/255., color[2]/255.]

def extractTriaFacets(master, emptyChain=[]):
    master = extractFacets(master,emptyChain)
    master = quads2tria(master)
    return master;

def objExporter((V,FV), filePath):
    out_file = open(filePath,"w")
    out_file.write("# List of Vertices:\n")
    for v in V:
        out_file.write("v")
        for c in v:
            out_file.write(" " + str(c))
        out_file.write("\n")
    out_file.write("# Face Definitions:\n")
    for f in FV:
        out_file.write("f")
        for v in f:
            out_file.write(" " + str(v+1))
        out_file.write("\n")
    out_file.close();

def extractFacets(master, emptyChain=[]):
    solidCV = [cell for k,cell in enumerate(master[1]) if not (k in emptyChain)]
    exteriorCV =  [cell for k,cell in enumerate(master[1]) if k in emptyChain]
    exteriorCV += exteriorCells(master)
    CV = solidCV + exteriorCV
    V = master[0]
    FV = [f for f in larFacets((V,CV),3,len(exteriorCV))[1] if len(f) >= 4]
    BF = boundaryCells(solidCV,FV)
    boundaryFaces = [FV[face] for face in BF]
    B_Rep = V,boundaryFaces
    return B_Rep



#Camera1
master = assemblyDiagramInit([5,3,2])([[.1,1.5,1,1.5,.1],[.1,3,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc= cellNumbering (master,hpc)(range(len(CV)),CYAN,2)



#Porta
toMerge = 23
diagram0 = assemblyDiagramInit([3,1,2])([[1,3,1],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Finestra
toMerge = 13
diagram0 = assemblyDiagramInit([1,1,3])([[3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)


emptyChain = [30,35,9,14,20]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\camera01.obj');




#Camera2
master = assemblyDiagramInit([5,3,2])([[.1,1.5,1,1.5,.1],[.1,3,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc= cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Porta
toMerge = 19
diagram0 = assemblyDiagramInit([3,1,2])([[1,3,1],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Finestra
toMerge = 17
diagram0 = assemblyDiagramInit([1,1,3])([[3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)


emptyChain = [30,35,9,15,19]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\camera02.obj');
#Traslazione camera2=T(2)(5)(camera2)


#Bagno2
master = assemblyDiagramInit([3,5,2])([[.1,2.2,.1],[.1,.4,0.9,.4,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Porta
toMerge = 25
diagram2 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram2,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)


#Finestra
toMerge = 5
diagram0 = assemblyDiagramInit([1,1,3])([[3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,1)

emptyChain = [14,16,12,28,31]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\bagno01.obj');

#Traslazione bagno=T(2)(3.1)(bagno)

#Stireria
master = assemblyDiagramInit([5,3,2])([[.1,1.4,1,.5,.1],[.1,2.1,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Porta
toMerge = 13
diagram3 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram3,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Finestra
toMerge = 16
diagram0 = assemblyDiagramInit([3,1,3])([[3,3,3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [34,28,9,14,19,31,37]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\stireria01.obj');

#Rimozione stireria=T([1,2])([10.8,8.2])(stireria)

#Cucina
master = assemblyDiagramInit([5,6,2])([[.1,1,1,2,.1],[.1,1.5,1,1.2,0.3,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)
#hpc4=T([1,2])([13.2,4.1])(hpc4)


#Finestra
toMerge = 53
diagram = assemblyDiagramInit([1,1,3])([[3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Porta
toMerge = 35
diagram = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Porta
toMerge = 25
diagram = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Porta
toMerge = 7
diagram = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [61,63,57,31,59,14,25,36,16,27,38,18,29,40,20,31,42]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\cucina.obj');

#Traslazione cucina=T([1,2])([13.2,4.1])(cucina)

#Bagno2
master = assemblyDiagramInit([5,3,2])([[.1,1.4,1,.4,.1],[.1,1.2,.1],[.1,2.7]])
V,CV = master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)
#hpc=T([1,2])([10.6,5.3])(hpc)

#Porta
toMerge = 17
diagram = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [29,15,9,20]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\bagno2.obj');

# Traslazione bagno2=T([1,2])([10.2,5.3])(bagno2)


#Scale
master = assemblyDiagramInit([3,5,2])([[.1,3.4,.1],[.1,.2,1,.2,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Porta
toMerge = 25
diagram2 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram2,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Finestra
toMerge = 19
diagram0 = assemblyDiagramInit([3,1,3])([[3,3,3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [28,15,34,13,17,14]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\scale.obj');

# Traslazione scale=T([1,2])([4,6.6])(scale)

#Remove
master = assemblyDiagramInit([3,3,2])([[.1,1.3,.1],[.1,1.4,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)

#Porta
toMerge = 7
diagram2 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram2,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)


emptyChain = [3,8,14,5,10,16,17]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\remove01.obj');

#Traslazione remove=T([1,2])([7.6,6.6])(remove)




#Soggiorno2
master = assemblyDiagramInit([5,3,2])([[.15,0.8,1.6,0.8,.15],[.1,6.0,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Porta
toMerge = 13
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [29,3,9,14,20,26,11,16,22]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\Soggiorno01.obj');

#Traslazione soggiorno2=T([1,2])([4.1,0.5])(soggiorno2)

#Soggiorno3
master = assemblyDiagramInit([7,4,2])([[.4,1,1,0.9,1,1,.3],[.1,.4,4.8,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc= cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Porta
toMerge = 41
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)
#Porta
toMerge = 33
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)
#Porta
toMerge = 17
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)
#Porta
toMerge = 9
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [58,56,54,52,5,12,19,27,34,41,49,7,14,21,29,36,43,51,10,17,32,39]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\Soggiorno02.obj');

#Traslazione soggiorno3=T([1])([7.6])(soggiorno3)

#Sala pranzo
master = assemblyDiagramInit([5,3,2])([[.1,1,2,1,.1],[.1,3.5,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)



#Finestra
toMerge = 13
diagram0 = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

emptyChain = [29,9,14,20,3,28,5,11,16,22,23]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\SalaPranzo.obj');

#Traslazione soggiorno4=T([1,2])([13.2,0.5])(soggiorno4)



#Remove2
master = assemblyDiagramInit([4,5,2])([[.1,3.6,2.6,.1],[.1,2.8,1.1,1.1,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)


#Finestra
toMerge = 19
diagram0 = assemblyDiagramInit([3,1,3])([[3,3,3],[.1],[1,1.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)

#Finestra
toMerge = 7
diagram = assemblyDiagramInit([1,1,2])([[2],[.1],[2.2,.5]])
master = diagram2cell(diagram,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)



emptyChain = [47,42,12,21,14,23,16,25,3,10,1,19,31,29,33,35,37,27]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\Remove02.obj');

#Traslazione remove2=T([1,2])([7.5,5.3])(remove2)



#Remove3
master = assemblyDiagramInit([3,5,2])([[.1,1.6,.1],[.1,.2,1.2,.2,.1],[.1,2.7]])
V,CV= master
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(CV)),CYAN,2)



toMerge = 25
diagram0 = assemblyDiagramInit([1,1,2])([[.1],[.1],[2.2,.5]])
master = diagram2cell(diagram0,master,toMerge)
hpc = SKEL_1(STRUCT(MKPOLS(master)))
hpc = cellNumbering (master,hpc)(range(len(master[1])),CYAN,2)


emptyChain = [29,19,17,15,13,11,9,7,5,3,1]
master_factes_tria = extractTriaFacets(master, emptyChain)
objExporter(master_factes_tria, 'C:\\Users\\Pietro\\Desktop\\Remove03.obj');

#Traslazione remove3=T([1,2])([2.4,3.1])(remove3)













