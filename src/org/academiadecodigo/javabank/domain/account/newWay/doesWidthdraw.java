package org.academiadecodigo.javabank.domain.account.newWay;

public interface doesWidthdraw {

    public boolean canWithdraw();

}

class ItWidthdraws implements doesWidthdraw{

    @Override
    public boolean canWithdraw() {
        return true;
    }
}

class itDoesNotWithdraws implements doesWidthdraw{

    @Override
    public boolean canWithdraw() {
        return false;
    }
}
