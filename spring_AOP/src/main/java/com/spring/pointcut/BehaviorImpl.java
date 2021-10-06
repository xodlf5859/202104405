package com.spring.pointcut;

public class BehaviorImpl implements Behavior {

	@Override
	public void 잠자기() {
		System.out.println("드르렁");
	}

	@Override
	public void 공부하기() {
		System.out.println("영차영차");

	}

	@Override
	public void 밥먹기() {
		System.out.println("쩝쩝");

	}

	@Override
	public void 데이트() {
		System.out.println("데이트 하고싶다");

	}

	@Override
	public void 운동() {
		System.out.println("운동 하고싶다");
		// TODO Auto-generated method stub

	}

	@Override
	public void 놀기() {
		System.out.println("놀고싶다");
		// TODO Auto-generated method stub

	}

	@Override
	public void 저인수양() {
		System.out.println("정신수양하고싶다");
		// TODO Auto-generated method stub

	}

}
