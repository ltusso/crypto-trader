import React from "react";
import TradeButton from "../trade_button_component/TradeButtonComponent";

export const Card = (props) => {
  function Variation(props) {
    const variation = props.variation;
    if (variation > 0) {
      return <img src="bullet_green.png" />;
    } else if (variation < 0) {
      return <img src="bullet_red.png" />;
    } else {
      return <img src="equals.png" />;
    }
  }

  return (
    <div class="card" Style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">{props.crypto.id}</h5>
        <h6 class="card-subtitle mb-2 text-muted">{props.crypto.priceUsd}</h6>
        <p class="card-text">{props.crypto.symbol}</p>
        <p>
          {props.crypto.variation}
          {"%"}
          <Variation variation={props.crypto.variation} />{" "}
        </p>
        <TradeButton key={props.crypto.id} crypto={props.crypto} type="buy" />
      </div>
    </div>
  );
};
export default Card;
