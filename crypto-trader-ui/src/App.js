import React, { Component } from "react";
import CoinListComponent from "./components/coin_list_component/CoinListComponent";
import UserDetailComponent from "./components/user_detail_component/UserDetailComponent";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

export default function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/coins">Coins</Link>
            </li>
            <li>
              <Link to="/operations">My operations</Link>
            </li>
          </ul>
        </nav>
        {/* A <Switch> looks through its children <Route>s and
          renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/coins">
            <CoinListComponent />
          </Route>
          <Route path="/operations">
            <UserDetailComponent customerId="1" />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
