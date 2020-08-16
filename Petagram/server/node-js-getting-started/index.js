const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 5000

var bodyParser = require("body-parser");
var admin = require('firebase-admin');
var FCM = require('fcm-push');

var tokenDevicesURI = "token-device";
var likesURI = "get-likes";

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
  .post("/" + likesURI,function(request,response){

    var token = request.body.token;
    var id_instagram = request.body.id_instagram;
    var id_foto = request.body.id_foto;

    var db = admin.database();
    var likeId = db.ref(likesURI).push();

    likeId.set({
      token: token,
      id_instagram : id_instagram,
      id_foto: id_foto
    })

    var path = likeId.toString();
    var pathSplit = path.split(likesURI + "/");
    var idItem = pathSplit[1];

    var respuesta = generarRespuestaLike(db , idItem);

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


  function generarRespuestaLike(db, idItem){

    var respuesta= {};
    var usuario="";
    var ref = db.ref("get-likes");

    ref.on("child_added" , function(snapshot, prevChildKey){
      usuario = snapshot.val();
      respuesta = {
        id : idItem , 
        token: usuario.token,
        id_instagram: usuario.id_instagram,
        id_foto: usuario.id_foto
      };
      enviarNotificacion(usuario.token,usuario.id_instagram);
    });

    return respuesta;
  }

  function enviarNotificacion(token, usuario){
    var serverKey = 'AAAARh4Y5h0:APA91bHJhuJKDZdLeLYbf0uwYXvruAqLcpODtVrGgAPL2Mmf8fPJSO7-7aHu5EZJjFCX5VPaKAEAZMYU51QCEC6xBys-Rx-yTvfitnyzeqHBF6u5vHcaU5ybtfTycOAzbZqKSmE4W4D3'; //put your server key here
    var fcm = new FCM(serverKey);

    var message = { //this may vary according to the message type (single recipient, multicast, topic, et cetera)
      to: token, 
      collapse_key: '',
      data: {} ,
      notification: {
          title: 'Recibiste un Like', 
          body: usuario + " Te a dado Like",
          icon: "huella" 
      }
      
      
  };
  
  fcm.send(message, function(err, response){
      if (err) {
          console.log("Something has gone wrong!");
      } else {
          console.log("Successfully sent with response: ", response);
      }
  });
  }
