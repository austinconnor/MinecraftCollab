import './App.css';
import logo from './heapcraftLogo.png'

function NavBar(){
    return(
        <div className="nav">
            <img src={logo} className="logo"></img>
            <h1 className="navText">HeapCraft HeatMap</h1>
        </div>
    );
}

export default NavBar;