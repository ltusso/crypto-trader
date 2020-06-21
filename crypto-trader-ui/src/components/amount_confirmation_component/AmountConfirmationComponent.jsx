import React from "react";
import { Button } from "react-bootstrap";

export const AmountConfirmation = (props) => (
  <div>
    <div>
      <input placeholder="Enter amount" />
    </div>
    <div>
      <Button
        onClick={() =>
          props.onPurchase(
            props.crypto.id,
            props.crypto.symbol,
            props.crypto.priceUsd,
            1,
            2
          )
        }
      >
        Confirm
      </Button>
    </div>
  </div>
);
export default AmountConfirmation;
