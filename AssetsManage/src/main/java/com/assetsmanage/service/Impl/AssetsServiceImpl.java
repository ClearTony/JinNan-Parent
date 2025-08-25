package com.assetsmanage.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.assetsmanage.common.Result;
import com.assetsmanage.dto.AssetsDto;
import com.assetsmanage.dto.AssetsExportDto;
import com.assetsmanage.dto.AssetsImportDto;
import com.assetsmanage.entity.Account;
import com.assetsmanage.entity.Assets;
import com.assetsmanage.mapper.AssetsMapper;
import com.assetsmanage.mapper.DepartmentMapper;
import com.assetsmanage.mapper.StaffMapper;
import com.assetsmanage.service.AssetsService;
import com.assetsmanage.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * 资产信息业务处理
 **/
@Service
public class AssetsServiceImpl implements AssetsService {

	@Autowired
	private AssetsMapper assetsMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	private static final Logger log = LoggerFactory.getLogger(AssetsServiceImpl.class);

	/**
	 * 新增
	 */
	public void add(AssetsDto assetsDto) {
		Assets assets = new Assets();
		BeanUtils.copyProperties(assetsDto, assets);
		if (assetsDto.getDepartmentIds() != null) {
			List<String> departmentIds = assetsDto.getDepartmentIds();
			assets.setDepartmentId(String.join("/", departmentIds));
		}
		Account currentUser = TokenUtils.getCurrentUser();
		assets.setOperator(currentUser.getId());
		assetsMapper.insert(assets);
	}

	/**
	 * 删除
	 */
	public void deleteById(Integer id) {
		assetsMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> ids) {
		for (Integer id : ids) {
			assetsMapper.deleteById(id);
		}
	}

	/**
	 * 修改
	 */
	public void updateById(AssetsDto assetsDto) {
		Assets assets = assetsMapper.selectById(assetsDto.getId());
		BeanUtils.copyProperties(assetsDto, assets);
		if (assetsDto.getDepartmentIds() != null) {
			List<String> departmentIds = assetsDto.getDepartmentIds();
			assets.setDepartmentId(String.join("/", departmentIds));
		}
		assetsMapper.updateById(assets);
	}

	/**
	 * 根据ID查询
	 */
	public Assets selectById(Integer id) {
		return assetsMapper.selectById(id);
	}

	/**
	 * 查询所有
	 */
	public List<AssetsDto> selectAll(Assets assets) {
		Account currentUser = TokenUtils.getCurrentUser();
		assets.setOperator(currentUser.getId());
		return assetsMapper.selectAll(assets);
	}

	/**
	 * 分页查询
	 */
	public PageInfo<AssetsDto> selectPage(Assets assets, Integer pageNum, Integer pageSize) {
		Account currentUser = TokenUtils.getCurrentUser();
		assets.setOperator(currentUser.getId());
		PageHelper.startPage(pageNum, pageSize);
		List<AssetsDto> list = assetsMapper.selectAll(assets);
		list.forEach(assetsDto -> {
			if (assetsDto.getDepartmentId() != null) {
				List<String> departmentId = Arrays.asList(assetsDto.getDepartmentId().split("/"));
				assetsDto.setDepartmentIds(departmentId);
				if (!departmentId.isEmpty()) {
					List<String> nameList =  departmentMapper.selectByIdList(departmentId);
					assetsDto.setDepartmentNames(nameList);
					assetsDto.setDepartmentName(String.join("/", nameList));
				}
			}
		});
		return PageInfo.of(list);
	}


	public int selectCountByCategory(String category) {
		return assetsMapper.selectCountByCategory(category);
	}

	@Transactional(rollbackFor = Exception.class)
	public Result importData(MultipartFile file) {
		Account currentUser = TokenUtils.getCurrentUser();
		List<Assets> assetsList = new ArrayList<>();
		try (InputStream inputStream = file.getInputStream()) {
			EasyExcel.read(inputStream, AssetsImportDto.class, new PageReadListener<AssetsImportDto>(dataList -> {
				for (AssetsImportDto assetsImportDto : dataList) {
					Assets assets = new Assets();
					assets.setOperator(currentUser.getId());
					BeanUtils.copyProperties(assetsImportDto, assets);
					String departmentName = assetsImportDto.getDepartmentName();
					if (departmentName!=null) {
						List<String> departmentArr = Arrays.asList(departmentName.split("/"));
						List<Integer> departmentIdList = departmentMapper.selectByName(departmentArr);
						if (!departmentIdList.isEmpty()) {
							String result = String.join("/", departmentIdList.stream().map(String::valueOf).toArray(String[]::new));
							assets.setDepartmentId(result);
						}
					}
					assetsList.add(assets);
				}
			})).sheet().doRead();

			int totalCount = assetsList.size();
			int successCount = 0;
			int failCount = 0;

			try {
				int inserted = assetsMapper.insertList(assetsList);
				successCount = inserted;
				failCount = totalCount - inserted;
			} catch (Exception e) {
				log.error("批量导入失败，开始逐条导入: {}", e.getMessage());
				for (Assets assets : assetsList) {
					try {
						assetsMapper.insert(assets);
						successCount++;
					} catch (Exception ex) {
						failCount++;
						log.error("单条导入失败: {}", ex.getMessage());
					}
				}
			}

			Map<String, Object> result = new HashMap<>();
			result.put("totalCount", totalCount);
			result.put("successCount", successCount);
			result.put("failCount", failCount);
			return Result.success(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.error();
		}
	}

	public List<AssetsExportDto> selectExportData() {
		Account currentUser = TokenUtils.getCurrentUser();
		Assets assets = new Assets();
		assets.setOperator(currentUser.getId());
		List<AssetsExportDto> list = assetsMapper.selectExportData(assets);
		list.forEach(assetsDto -> {
			if (assetsDto.getDepartmentId() != null) {
				List<String> departmentId = Arrays.asList(assetsDto.getDepartmentId().split("/"));
				if (!departmentId.isEmpty()) {
					List<String> nameList =  departmentMapper.selectByIdList(departmentId);
					assetsDto.setDepartmentName(String.join("/", nameList));
				}
			}
		});
		return list;
	}
}