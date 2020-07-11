import React, { Component } from "react";
import CoinListComponent from "./components/coin_list_component/CoinListComponent";
import PurchaseListComponent from "./components/purchase_list_component/PurchaseListComponent";
import CustomerInfoComponent from "./components/customer_info_component/CustomerInfoComponent";
import CustomerAssetComponent from "./components/customer_assets_component/CustomerAssetComponent";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";

export default function App() {
  return (
    <Router>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="#home">Crypto trading emulator</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="/coins">Coins</Nav.Link>
            <Nav.Link href="/operations">Operations</Nav.Link>
            <Nav.Link href="/assets">Assets</Nav.Link>
          </Nav>
        </Navbar.Collapse>
        <div inline>
          {" "}
          <CustomerInfoComponent customerId="1" />
        </div>
      </Navbar>

      {/* A <Switch> looks through its children <Route>s and
          renders the first one that matches the current URL. */}
      <Switch>
        <Route path="/coins">
          <CoinListComponent />
        </Route>
        <Route path="/operations">
          <PurchaseListComponent customerId="1" />
        </Route>
        <Route path="/assets">
          <CustomerAssetComponent customerId="1" />
        </Route>
      </Switch>
    </Router>
  );
}
