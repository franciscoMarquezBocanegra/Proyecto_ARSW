import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import "./Login.css";
import { BrowserRouter, Route, Routes, Link } from "react-router-dom";
import React from "react";
import Login from "./Login";
import Profile from "./Profile";
import SignUp from "./SignUp";
import ViewComprador from "./ViewComprador";
import authenticationService from "./services/authenticationService";
import ProductsByVendedor from "./ProductsByVendedor";
import ViewVendedor from "./ViewVendedor";
import ListProducts from "./ListProducts";
import AgregarProducto from "./AgregarProducto";
import Subasta from "./Subasta";

/* The App class is the main component of the application, it renders the navbar and the routes of the
application */
export class App extends React.Component {
  /**
   * The constructor function is a special method for creating and initializing an object created
   * within a class.
   * @param props - The props that are passed to the component.
   */
  constructor(props) {
    super(props);
    this.handleLogOut = this.handleLogOut.bind(this);
    this.state = {
      viewComprador: false,
      viewVendedor: false,
      actualUser: undefined,
    };
  }

  /**
   * If the user is logged in, then set the state of the component to the user's information.
   */
  componentDidMount() {
    const usuario = authenticationService.getActualUser();
    if (usuario) {
      this.setState({
        actualUser: usuario,
        viewComprador: usuario.roles.includes("Comprador"),
        viewVendedor: usuario.roles.includes("Vendedor"),
      });
    }
  }

  /**
   * When the user clicks the logout button, the authentication service is called to logout, the user
   * is redirected to the home page, and an alert is displayed.
   */
  handleLogOut() {
    authenticationService.logOut();
    window.location = "/";
    alert("Sesion cerrada exitosamente");
  }

  render() {
    const { actualUser, viewComprador, viewVendedor } = this.state;
    return (
      <BrowserRouter>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
              <li className="nav-item active">
                <Link className="nav-link" to={"/productos"}>
                  Productos
                </Link>
              </li>
              {viewVendedor && (
                <li className="nav-item">
                  <Link to={"/vendedor"} className="nav-link">
                    vistaVendedor
                  </Link>
                </li>
              )}
              {viewComprador && (
                <li className="nav-item">
                  <Link to={"/comprador"} className="nav-link">
                    vistaComprador
                  </Link>
                </li>
              )}
            </ul>
            {actualUser ? (
              <div className="navbar-nav float-right">
                <li className="nav-item">
                  <Link to={"/profile"} className="nav-link">
                    {actualUser.username}
                  </Link>
                </li>

                <li className="nav-item">
                  <Link
                    to={"/"}
                    className="nav-link"
                    onClick={this.handleLogOut}
                  >
                    LogOut
                  </Link>
                </li>
              </div>
            ) : (
              <div className="navbar-nav">
                <li className="nav-item">
                  <Link to={"/signup"} className="nav-link">
                    Registrate
                  </Link>
                </li>
              </div>
            )}
          </div>
        </nav>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/comprador" element={<ViewComprador />} />
          <Route path="/productsbyVendedor/:vendedor" element={<ProductsByVendedor />}/>
          <Route path="/vendedor" element={<ViewVendedor />} />
          <Route path="/productos" element={<ListProducts />} />
          <Route path="/agregar" element={<AgregarProducto />} />
          <Route path="/subasta/:producto/:precio" element={<Subasta />} />
        </Routes>
      </BrowserRouter>
    );
  }
}

export default App;
