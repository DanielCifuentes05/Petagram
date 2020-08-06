const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 5000

var bodyParser = require("body-parser");
var admin = require('firebase-admin');

var tokenDevicesURI = "token-device";

admin.initializeApp({
  credential: admin.credential.cert("petagram-a9f55-firebase-adminsdk-4963x-68583a3e7f.json"),
  databaseURL: "https://petagram-a9f55.firebaseio.com"
});

express()
  .use(express.static(path.join(__dirname, 'public')))
  .use(bodyParser.json())
  .use(bodyParser.urlencoded({extended : true}))
  .set('views', path.join(__dirname, 'views'))
  .set('view engine', 'ejs')
  .get('/android', (req, res) => res.render('pages/index'))
  .post("/" + tokenDevicesURI,function(request,response){
    
    var token = request.body.token;
    var id_instagram = request.body.id_instagram;

    var db = admin.database();
    var tokenDevices = db.ref(tokenDevicesURI).push();

    tokenDevices.set({
      token: token,
      id_instagram : id_instagram
  
    });

    var path = tokenDevices.toString();
    var pathSplit = path.split(tokenDevicesURI + "/");
    var idItem = pathSplit[1];

    var respuesta = generarRespuestaToken(db , idItem);

    response.setHeader("Content-Type" , "application/json");
    response.send(JSON.stringify(respuesta));

  })
  .listen(PORT, () => console.log(`Listening on ${ PORT }`))




  function generarRespuestaToken(db, idItem){

    var respuesta= {};
    var usuario="";
    var ref = db.ref("token-device");

    ref.on("child_added" , function(snapshot, prevChildKey){
      usuario = snapshot.val();
      respuesta = {
        id : idItem , 
        token: usuario.token,
        id_instagram: usuario.id_instagram
      };
    });

    return respuesta;
  }
