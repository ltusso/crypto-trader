import React from "react";
import { Button } from "react-bootstrap";

export const AmountConfirmation = (props) => (
  <div>
    <div>
      <input
        placeholder="Enter amount"
        id="purchasedAmount"
        onChange={props.onAmountPurchased}
      />
    </div>
    <div>
      <Button
        onClick={() =>
          props.onPurchase(
            props.crypto.id,
            props.crypto.symbol,
            props.crypto.priceUsd
          )
        }
      >
        Confirm
      </Button>
      &nbsp;
      <Button onClick={props.onCancelPurchase}>Cancel</Button>
    </div>
  </div>
);
export default AmountConfirmation;
