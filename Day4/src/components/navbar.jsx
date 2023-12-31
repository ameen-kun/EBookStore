import "./navbar.css";
import logo from "../assets/images/logo.svg";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import { HomeOutlined,SearchOutlined } from "@mui/icons-material";
import AutoStoriesOutlinedIcon from '@mui/icons-material/AutoStoriesOutlined';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import { Person4Outlined } from "@mui/icons-material";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../redux/loginSlice";


export default function CustomNavbar(props){

    const dispatch=useDispatch();
    const nav=useNavigate();
    useEffect(()=>{
        document.getElementById(props.currentPage).style.backgroundColor="black";
        document.getElementById(props.currentPage).style.color="white";
    },[props])
    const logout=()=>{
        dispatch(logoutUser);
        nav("/");
    }
    return(
        <div className="navbar-outer">
            <div className="navbar-img">
                <img id="navbar-logo" src={logo} alt="logo"/>
            </div>
            <div className="navbar-right">
                <div className="user-display">
                    <p className="nav-welcome">Welcome {props.name} !</p>
                    <div id="nav-logout">
                        <LogoutOutlinedIcon onClick={logout}id="nav-logout-icon"/>
                    </div>
                </div>
                <div className="navbar-buttons">
                    <Link to="/home" className="route">
                    <button id="home"className="nav-butt"><HomeOutlined/><p>Home</p></button>
                    </Link>
                    <button id="explore" className="nav-butt"><SearchOutlined/><p>Explore</p></button>
                    <button id="my-books" className="nav-butt"><AutoStoriesOutlinedIcon/><p>My Books</p></button>
                    <button id="my-cart" className="nav-butt"><ShoppingCartOutlinedIcon id="shopping-outlined"/></button>
                    <button id="my-profile" className="nav-butt"><Person4Outlined id="person-outlined"/></button>
                </div>
            </div>
        </div>
    )
}