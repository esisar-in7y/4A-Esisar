clear all
load('attack_data_10k.mat');
load('constants.mat');
%######################################################################################

mode = 1; % Use mode = 1 for running only the current script
if (mode == 1)
    datapoints2 = datapoints;
    datapoints2 = datapoints2*1000000;
    byte_to_attack = 1; % Use this to add a for loop and loop over all 16 bytes.
    samples = 1000;%size(datapoints2,1);%samples = size(aligned_samples,1); % 128;
elseif (mode == 0)
    datapoints2 = datapoints(num_trace_start:num_trace_stop,:);
    datapoints2 = datapoints2*1000000;
    %byte_to_attack = 6; % Use this to add a for loop and loop over all 16 bytes.
    samples = numoftraces;
elseif (mode ==2)
    datapoints2 = datapoints;
    datapoints2 = datapoints2*1000000;
    samples = size(datapoints2,1);
end
more off
for samples=500:100:2000
    answers = []
    for byte_to_attack=1:16
        % Prepare data
        D = plaintexts_SCA(1:samples,byte_to_attack);
        %clear aes_plaintexts byte_to_attack
        
        % Prepare power traces
        %sprintf('traces = %s(1:samples, :);', datapoints2);
        traces = datapoints2(1:samples,:);
        %clear analyzed_traces
        
        % Prepare keys
        K = uint8(0:255);
        
        % Calculate hypothetical intermediate values
        V = zeros(length(D),length(K));
        for k = 1:length(K)
            for j = 1:length(D)
                V(j,k)=SubBytes(bitxor(D(j),K(1,k))+1);
            end
        end
        
        % Calculate hypothetical power consumption
        H = V;
        for k = 1:size(H,2)
            for j =1:size(H,1)
                H(j,k)= HW(V(j,k)+1);
            end
        end
        
        % Calculate the correlation
        trace_length = size(traces, 2);
        R = zeros(length(K), trace_length);
        for key_index = 1:length(K)
            if (mode == 1) fprintf('Working on key guess = %d\n', K(key_index)); end
            
            for k = 1:trace_length
                m = corrcoef(H(:,key_index),traces(:,k));
                R(key_index,k) = m(1,2);
            end    
        end
        clear key_index k r
        
        [M,I] = max(abs(R(:)))
        [key_row, key_col] = ind2sub(size(R),I)
        key_found = key_row - 1
        answers(byte_to_attack)= key_found;
        if key_found ~= key_for_matlab_computation_dec(1,byte_to_attack)
            break
        end
        % s1 = 'DPA attack - experiment: ';
        % s3 = ' - Design under attack: Tiny AES (GitHub)';
        % s4 = ' - key byte = ';
        % s5 = num2str(byte_to_attack,'%d');
        % s6 = ' - Date: ';
        % s7 = date;
        % plot_title = strcat(s1,s3,s4,s5,s6,s7);
        %file_title = strcat('Tiny_AES_DPA_attack_','_key_byte',s5,'.png');
        % figure
        % hold on
        % % write your code here...
        % for k = 1:256
        %     plot(R(k,:))
        % end
        % hold off
        
    
        % title(plot_title);
        % xlabel('Key Hypothesis');
        % ylabel('Correlation');
        % grid on;
        % print(file_title, '-dpng', '-r600');
        % maxcorr = 0;
        % figure
        % hold on
        % for k = 1:size(R,2)
        %     plot(R(:,k))
        % end
        % hold off
    end
    if isequal(answers,key_for_matlab_computation_dec)
        return
    end
end





