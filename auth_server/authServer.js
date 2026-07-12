const express = require("express");
const jwt = require("jsonwebtoken");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

app.use(bodyParser.json());
app.use(cors());

app.use((req,res,next)=>{
  console.log("REQUEST RECEIVED:", req.method, req.url);
  next();
});

const SECRET_KEY = "mysecret";

let deviceTokens = [];

/*GENERATE TOKEN*/
app.post("/generate-token", (req, res) => {

  const { username, password } = req.body;

  // Dummy users database
  const users = {
    sinchana: { password: "123", roles: ["ADMIN"] },
    verifier: { password: "123", roles: ["VERIFIER"] },
    manager: { password: "123", roles: ["MANAGER"] },
    rep: { password: "123", roles: ["REPRESENTATIVE"] }
  };

  const user = users[username];

  if (!user || user.password !== password) {
    return res.status(401).json({
      message: "Invalid username or password"
    });
  }

  const token = jwt.sign(
    { username: username, roles: user.roles },
    "mysupersecretkey",
    { expiresIn: "15m" }
  );

  res.json({
    token: token
  });

});

/* -------------------------------
   VERIFY TOKEN
--------------------------------*/
app.post('/verify-token', (req, res) => {

  const token = req.headers.authorization?.replace('Bearer ', '');

  if (!token) {
    return res.status(401).json({ valid: false });
  }

  try {

    const decoded = jwt.verify(token, 'mysupersecretkey');

    res.json({
      valid: true,
      user: decoded
    });

  } catch (error) {

    res.json({
      valid: false
    });

  }

});

app.post("/save-device-token", (req,res)=>{

  console.log("SAVE DEVICE TOKEN REQUEST RECEIVED");

  console.log("REQUEST BODY:", req.body);

  const {username, token} = req.body;

const existing = deviceTokens.find(
  item => item.username === username
);

if(existing){
  existing.token = token;
}
else{
  deviceTokens.push({
    username,
    token
  });
}

  console.log(
    "DEVICE TOKEN SAVED:",
    username,
    token
  );


  res.json({
    message:"Token saved"
  });

});

/* -------------------------------
   START SERVER
--------------------------------*/
app.listen(4000, "0.0.0.0", () => {
    console.log("Auth Server running on port 4000");
});