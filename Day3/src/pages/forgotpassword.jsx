import { useState } from "react";
import { Form } from "react-bootstrap";
import Row from "react-bootstrap/Row";
import "../assets/styles/forgotpassword.css";
import { InputGroup } from "react-bootstrap";
import logo from "../assets/images/logo.svg";
import usericon from "../assets/images/outline_person_black_24dp.png";
import emailicon from "../assets/images/outline_email_black_24dp.png";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import Name from "../components/name";
function ForgotPassword(){

    const [username,setUserName]=useState("");
    const [email,setEmail]=useState("");
    const submitForm=()=>{
        console.log("Hello");
    }
    return(
        <div className="forgot-outer">
            <div className="forgot-box">
                <div className="forgot-form">
                <Form noValidate onSubmit={submitForm}>
                <h3>Forgot Password</h3>
                <Row className="mb-3">
                <Form.Group controlId="uname">
                    <Form.Label>Username</Form.Label>
                        <InputGroup className="mb-3">
                            <InputGroup.Text id="basic-addon1"><img src={usericon} alt="@"/></InputGroup.Text>                                    <Form.Control type="text"  placeholder="Username" value={username} onChange={(e)=>{setUserName(e.target.value)}}/>
                        </InputGroup>
                        </Form.Group>
                </Row>
                <Row className="mb-3">
                            <Form.Group controlId="email">
                                <Form.Label>E-mail</Form.Label>
                                <InputGroup className="mb-3">
                                    <InputGroup.Text id="basic-addon1"><img src={emailicon} alt="@"/></InputGroup.Text>
                                        <Form.Control type="email" placeholder="example@demo.com" value={email} onChange={(e)=>{setEmail(e.target.value)}} />
                                    </InputGroup>
                            </Form.Group>
                        </Row>
                <Row>
                    <Form.Group controlId="sumbit">
                        <Button id="forgot-button" variant="outline-dark" type="submit">Proceed</Button>
                    </Form.Group>
                </Row>
                <Row>
                    <Form.Text>An OTP Will Be Sent to the Registered Email.</Form.Text>
                </Row>
                <Row>
                    <Link to="/" className="route">
                        <Form.Text >Go Back?</Form.Text>
                    </Link>
                </Row> 
            </Form>
            </div>
        <div className="forgot-bar">
        </div>
        <div className="forgot-img">
            <img id="forgot-logo" src={logo} alt="logo"/>
        </div>
            </div>
            <Name/>
        </div>
    );
}
export default ForgotPassword;