package PGS_택배배달과수거기간;

/* 
 *  뒤에서부터 cap만큼 빼주는데 배달, 수거 둘 중 하나가 부족해서 해당 index칸을 다 지우지 못한다면
 *  양쪽에(deliverCount, pickupCount) cap만큼 추가, 이게 싫으면 그냥 i를 이전 꺼를 다시하면 될 듯
 *  cap만큼 추가해줄 때 현재 거리((index + 1) * 2)를 답에 더 해준다 
 *  해당 index를 완전히 0으로 만들고 다음꺼로 이동
 *  마지막에 거리를를 더 해주는 이유는 거리를 더 할 때가 들고 있는 것이 완전히 다 떨어졌는 떄 새로운 상자가 필요할 때 거리를 더 하는데
 *  마지막에는 다 떨어지든 아니든 새로운 상자가 필요하지 않기 때문에 더 해줘야함 
 */

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int distance = 0;
		int deliverCount = cap;
		int pickupCount = cap;
		distance = 0;
		
		for (int i = n-1; i >= 0; i--) {
			if (deliveries[i] > 0) {
				// 초기값일 때
				if (distance == 0) distance = (i + 1) * 2;
				// 현재 가진 것으로 처리 가능 할 떄
				if (deliverCount >= deliveries[i]) deliverCount -= deliveries[i];
				// 현재 가진 것으로 처리 불가능 할 때
				else {
					// 현재 index를 완전히 처리해주기 위함
					while(deliveries[i] != 0) {
						deliverCount += cap;
						pickupCount += cap;
						// 처리할 수 있으면 
						if(deliveries[i] >= deliverCount) {
							deliveries[i] -= deliverCount;
							deliverCount = 0;
						}
						// 처리할 수 없으면
						else {
							deliverCount -= deliveries[i];
							deliveries[i] = 0;
						}
						answer += distance;
						// 처리 못 할 경우에 distance를 갱신해준다
						distance = (i + 1) * 2;
					}
				}
			}
			
			//위랑 똑같음
			if (pickups[i] > 0) {
				if (distance == 0) distance = (i + 1) * 2;
				if (pickupCount >= pickups[i]) pickupCount -= pickups[i];
				
				else {
					while(pickups[i] != 0) {
					    deliverCount += cap;
					    pickupCount += cap;
					    if(pickups[i] >= pickupCount) {
						    pickups[i] -= pickupCount;
						    pickupCount = 0;
					    }
					    else {
						    pickupCount -= pickups[i];
						    pickups[i] = 0;
					    }
					    answer += distance;
					    distance = (i + 1) * 2;
					}
				}
			}

		}
		//  위의 로직으로는 마지막 것을 더해주지 않음
		answer += distance;

		return answer;
	}
}