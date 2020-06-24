import React from "react";
import BuyButton from "./../buy_button_component/BuyButtonComponent";

export const Card = (props) => (
  <div class="card" Style="width: 18rem;">
    <div class="card-body">
      <h5 class="card-title">{props.crypto.id}</h5>
      <h6 class="card-subtitle mb-2 text-muted">{props.crypto.priceUsd}</h6>
      <p class="card-text">{props.crypto.symbol}</p>
      <BuyButton key={props.crypto.id} crypto={props.crypto} />
    </div>
  </div>
);
export default Card;
